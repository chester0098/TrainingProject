package com.fadineg.trainingproject.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.news.model.News;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FiltersRecyclerAdapter extends RecyclerView.Adapter<FiltersRecyclerAdapter.ViewHolder> {
    private List<News> newsList;

    FiltersRecyclerAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    List<News> getNewsList() {
        return newsList;
    }

    @NotNull
    @Override
    public FiltersRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_filters_item, parent, false);
        return new FiltersRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NotNull final FiltersRecyclerAdapter.ViewHolder holder, final int position) {
        final News news = newsList.get(position);
        holder.category.setText(news.getCategoryName());
        holder.aSwitch.setChecked(news.getCategorySwitch());

        holder.aSwitch.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            newsList.get(position).setCategorySwitch(isChecked);
        });
    }


    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView category;
        Switch aSwitch;

        ViewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.rv_filters_title);
            aSwitch = itemView.findViewById(R.id.rv_filters_switch);
        }
    }
}
