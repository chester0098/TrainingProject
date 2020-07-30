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
import androidx.recyclerview.widget.RecyclerView;

import com.fadineg.trainingproject.R;


import org.jetbrains.annotations.NotNull;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Period;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {
    private List<News> newsList;
    private Context context;
    private static final String DEF_TYPE = "drawable";

    NewsRecyclerAdapter(List<News> helpList, Context context) {
        this.newsList = helpList;
        this.context = context;
    }

    List<News> getNewsList() {
        return newsList;
    }

    void setNewsList(List<News> newsList) {
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
        final News news = newsList.get(position);

        Resources res = holder.itemView.getContext().getResources();
        int imageId = res.getIdentifier(news.getImage(), DEF_TYPE, context.getPackageName());

        String date = null;
        if (news.getDate() == null) {
            date = timeStyle_period(news.getDate_start(), news.getDate_end());
        } else date = timeStyle_date(news.getDate());

        int limit = 100;
        String trimDescr = news.getDescription().length() > limit ? news.getDescription().substring(0, limit) : news.getDescription();

        holder.newsImage.setImageResource(imageId);
        holder.newsTitle.setText(news.getTitle());
        holder.newsDescription.setText(trimDescr + "...");
        holder.newsDate.setText(date);

        String finalDate = date;
        holder.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDescriptionActivity.class);
                intent.putExtra("Title", news.getTitle());
                intent.putExtra("Fund", news.getFund_name());
                intent.putExtra("Address", news.getAddress());
                intent.putExtra("Phone", news.getPhone());
                intent.putExtra("Image", imageId);
                intent.putExtra("Description", news.getDescription());
                intent.putExtra("Date", finalDate);
                context.startActivity(intent);
            }
        });
    }

    private String timeStyle_date(String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM, yyyy");
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return dtf.format(localDate);
    }

    private String timeStyle_period(String dateStart, String dateEnd) {
        DateTimeFormatter dtfOld = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dtfNew = DateTimeFormatter.ofPattern("dd.MM");
        LocalDate localDateStart = LocalDate.parse(dateStart, dtfOld);
        LocalDate localDateEnd = LocalDate.parse(dateEnd, dtfOld);
        int days = (int) ChronoUnit.DAYS.between(LocalDate.now(), localDateEnd);

        return "Осталось " + days + " дней ("
                + dtfNew.format(localDateStart) + " - "
                + dtfNew.format(localDateEnd) + ")";
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
