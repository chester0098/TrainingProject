package com.fadineg.trainingproject.search.events_search_fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.news.model.Articles;
import com.fadineg.trainingproject.news.news_fragment.NewsDiffUtilCallback;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.ViewHolder> {
    private List<Articles> eventsList;
    private List<Articles> updatedEventList;

    EventsRecyclerAdapter(List<Articles> nkoList) {
        this.eventsList = nkoList;
        this.updatedEventList = nkoList;
    }

    private List<Articles> getUpdatedEventList() {
        return updatedEventList;
    }

    private void setUpdatedEventList(List<Articles> updatedEventList) {
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
        final Articles articles = updatedEventList.get(position);
        holder.tvTitle.setText(articles.getName());
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


    private void updateEventsList(List<Articles> updatedNewsList) {
        NewsDiffUtilCallback newsDiffUtilCallback =
                new NewsDiffUtilCallback(getUpdatedEventList(), updatedNewsList);
        DiffUtil.DiffResult newsDiffResult = DiffUtil.calculateDiff(newsDiffUtilCallback);
        setUpdatedEventList(updatedNewsList);
        newsDiffResult.dispatchUpdatesTo(this);
    }

    void filterResults(String constraint) {
        List<Articles> results = new ArrayList<>();
        for (Articles articles : eventsList) {
            if (articles.getName().toLowerCase().contains(constraint)) {
                results.add(articles);
            }
        }
        updateEventsList(results);
    }
}
