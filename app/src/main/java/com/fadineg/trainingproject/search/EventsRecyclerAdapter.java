package com.fadineg.trainingproject.search;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.news.News;
import com.fadineg.trainingproject.news.NewsDiffUtilCallback;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.ViewHolder> {
    private List<News> eventsList;
    private List<News> updatedEventList;

    EventsRecyclerAdapter(List<News> nkoList) {
        this.eventsList = nkoList;
        this.updatedEventList = nkoList;
    }

    public List<News> getUpdatedEventList() {
        return updatedEventList;
    }

    public void setUpdatedEventList(List<News> updatedEventList) {
        this.updatedEventList = updatedEventList;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_search_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NotNull final ViewHolder holder, final int position) {
        final News news = updatedEventList.get(position);
        holder.tvTitle.setText(news.getTitle());
    }


    @Override
    public int getItemCount() {
        return updatedEventList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.rv_search_tv_title);

        }
    }


    private void updateEventsList(List<News> updatedNewsList) {
        NewsDiffUtilCallback newsDiffUtilCallback =
                new NewsDiffUtilCallback(getUpdatedEventList(), updatedNewsList);
        DiffUtil.DiffResult newsDiffResult = DiffUtil.calculateDiff(newsDiffUtilCallback);
        setUpdatedEventList(updatedNewsList);
        newsDiffResult.dispatchUpdatesTo(this);
    }

    void getFilteredResults(String constraint) {
        List<News> results = new ArrayList<>();
        for (News news : eventsList) {
            if (news.getTitle().toLowerCase().contains(constraint)) {
                results.add(news);
            }
        }
        updateEventsList(results);
    }
}
