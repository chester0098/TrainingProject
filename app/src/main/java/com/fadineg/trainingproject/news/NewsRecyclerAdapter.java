package com.fadineg.trainingproject.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.news.model.Articles;
import com.fadineg.trainingproject.news.model.Phone_numbers;


import org.jetbrains.annotations.NotNull;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {
    private List<Articles> newsList;
    private Context context;
    private static final String DEF_TYPE = "drawable";
    static final String EXTRA_TITLE = "Title";
    static final String EXTRA_FUND = "Fund";
    static final String EXTRA_ADDRESS = "Address";
    static final String EXTRA_PHONE = "Phone";
    static final String EXTRA_IMAGE = "Image";
    static final String EXTRA_DESCRIPTION = "Description";
    static final String EXTRA_DATE = "Date";

    private static final String DATE_TYPE_PERIOD = "time_period";
    private static final String DATE_TYPE_SINGLE = "single_day";

    NewsRecyclerAdapter(List<Articles> helpList, Context context) {
        this.newsList = helpList;
        this.context = context;
    }

    private List<Articles> getNewsList() {
        return newsList;
    }

    private void setNewsList(List<Articles> newsList) {
        this.newsList = newsList;
    }

    @NotNull
    @Override
    public NewsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_news_item, parent, false);
        return new NewsRecyclerAdapter.ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NotNull final NewsRecyclerAdapter.ViewHolder holder, final int position) {
        final Articles articles = newsList.get(position);

        String date = null;
        if (articles.getType().equals(DATE_TYPE_PERIOD)) {
            date = timeStylePeriod(articles.getDate_from(), articles.getDate_to());
        } else date = timeStyleDate(articles.getDate_to());


        StringBuilder phone = new StringBuilder();
        for (Phone_numbers phone_numbers : articles.getPhoneNumbers()){
            phone.append(phone_numbers.getNumber()).append('\n');
        }

        holder.newsTitle.setText(articles.getName());
        holder.newsDescription.setText(articles.getShortDescription());
        holder.newsDate.setText(date);

        String finalDate = date;
        holder.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDescriptionActivity.class);
                intent.putExtra(EXTRA_TITLE, articles.getName());
                intent.putExtra(EXTRA_FUND, articles.getOrgName());
                intent.putExtra(EXTRA_ADDRESS, articles.getAddress());
                intent.putExtra(EXTRA_PHONE, phone.toString());
                intent.putExtra(EXTRA_DESCRIPTION, articles.getDescription());
                intent.putExtra(EXTRA_DATE, finalDate);
                context.startActivity(intent);
            }
        });
    }

    private String timeStyleDate(String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM, yyyy");
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        return dtf.format(localDate);
    }

    private String timeStylePeriod(String dateStart, String dateEnd) {
        DateTimeFormatter dtfOld = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateTimeFormatter dtfNew = DateTimeFormatter.ofPattern("dd.MM");
        LocalDate localDateStart = LocalDate.parse(dateStart, dtfOld);
        LocalDate localDateEnd = LocalDate.parse(dateEnd, dtfOld);
        int days = (int) ChronoUnit.DAYS.between(LocalDate.now(), localDateEnd);

        return context.getString(R.string.Left) + " " + days + " " + context.getString(R.string.days)
                + dtfNew.format(localDateStart) + " - "
                + dtfNew.format(localDateEnd) + ")";
    }

    void updateNewsList(List<Articles> updatedArticlesList) {
        NewsDiffUtilCallback newsDiffUtilCallback =
                new NewsDiffUtilCallback(getNewsList(), updatedArticlesList);
        DiffUtil.DiffResult newsDiffResult = DiffUtil.calculateDiff(newsDiffUtilCallback);
        setNewsList(updatedArticlesList);
        newsDiffResult.dispatchUpdatesTo(this);
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewItem;
        ImageView newsImage;
        TextView newsTitle;
        TextView newsDescription;
        TextView newsDate;

        ViewHolder(View itemView) {
            super(itemView);
            cardViewItem = itemView.findViewById(R.id.rv_news_card);
            newsImage = itemView.findViewById(R.id.rv_news_image);
            newsTitle = itemView.findViewById(R.id.rv_news_title);
            newsDescription = itemView.findViewById(R.id.rv_news_description);
            newsDate = itemView.findViewById(R.id.rv_news_date);
        }
    }
}
