package com.fadineg.trainingproject.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fadineg.trainingproject.R;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {
    private List<News> newsList;

    NewsRecyclerAdapter(List<News> helpList) {
        this.newsList = helpList;
    }

    @NotNull
    @Override
    public NewsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_news_item, parent, false);
        return new NewsRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NotNull final NewsRecyclerAdapter.ViewHolder holder, final int position) {
        final News news = newsList.get(position);
        holder.newsImage.setImageResource(news.getNewsImage());
        holder.newsTitle.setText(news.getNewsTitle());
        holder.newsDescription.setText(news.getNewsDescription());
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsTitle;
        TextView newsDescription;

        ViewHolder(View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.rv_news_image);
            newsTitle = itemView.findViewById(R.id.rv_news_title);
            newsDescription = itemView.findViewById(R.id.rv_news_description);
        }
    }
}
