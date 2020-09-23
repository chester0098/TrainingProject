package com.fadineg.trainingproject.news.news_fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.news.model.Articles;
import com.fadineg.trainingproject.news.model.PhoneNumbers;

import org.jetbrains.annotations.NotNull;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {
    private List<Articles> newsList;
    private ItemClickListener listener;

    private static final String DEF_TYPE = "drawable";
    public static final String EXTRA_TITLE = "Title";
    public static final String EXTRA_FUND = "Fund";
    public static final String EXTRA_ADDRESS = "Address";
    public static final String EXTRA_PHONE = "Phone";
    public static final String EXTRA_IMAGE = "Image";
    public static final String EXTRA_DESCRIPTION = "Description";
    public static final String EXTRA_DATE = "Date";

    private static final String DATE_TYPE_PERIOD = "time_period";
    private static final String DATE_TYPE_SINGLE = "single_day";

    NewsRecyclerAdapter(List<Articles> helpList, ItemClickListener listener) {
        this.newsList = helpList;
        this.listener = listener;
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

    private static void timeStyleDate(String dateString, TextView dateTextView) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM, yyyy");
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        dateTextView.setText(dtf.format(localDate));
    }

    private static void timeStylePeriod(String dateStart, String dateEnd, TextView dateTextView) {
        DateTimeFormatter dtfOld = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateTimeFormatter dtfNew = DateTimeFormatter.ofPattern("dd.MM");
        LocalDate localDateStart = LocalDate.parse(dateStart, dtfOld);
        LocalDate localDateEnd = LocalDate.parse(dateEnd, dtfOld);
        int days = (int) ChronoUnit.DAYS.between(LocalDate.now(), localDateEnd);

        dateTextView.setText(dateTextView.getContext().getString(R.string.Left) + " " + days + " " + dateTextView.getContext().getString(R.string.days)
                + dtfNew.format(localDateStart) + " - "
                + dtfNew.format(localDateEnd) + ")");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NotNull final NewsRecyclerAdapter.ViewHolder holder, final int position) {
        final Articles articles = newsList.get(position);
        holder.bind(articles, listener);

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

        void bind(Articles articles, ItemClickListener listener) {
            if (articles.getType().equals(DATE_TYPE_PERIOD)) {
                timeStylePeriod(articles.getDate_from(), articles.getDate_to(), newsDate);
            } else timeStyleDate(articles.getDate_to(), newsDate);


            StringBuilder phone = new StringBuilder();
            for (PhoneNumbers phoneNumbers : articles.getPhoneNumbers()) {
                phone.append(phoneNumbers.getNumber()).append('\n');
            }

            newsTitle.setText(articles.getName());
            newsDescription.setText(articles.getShortDescription());

            cardViewItem.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putString(EXTRA_TITLE, articles.getName());
                bundle.putString(EXTRA_FUND, articles.getOrgName());
                bundle.putString(EXTRA_ADDRESS, articles.getAddress());
                bundle.putString(EXTRA_PHONE, phone.toString());
                bundle.putString(EXTRA_DESCRIPTION, articles.getDescription());
                bundle.putString(EXTRA_DATE, newsDate.getText().toString());
                listener.onItemClick(bundle);
            });
        }
    }
}
