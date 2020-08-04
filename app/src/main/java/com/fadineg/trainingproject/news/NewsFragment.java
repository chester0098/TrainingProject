package com.fadineg.trainingproject.news;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.fadineg.trainingproject.R;

import java.util.List;

public class NewsFragment extends Fragment {
    private NewsRecyclerAdapter newsRecyclerAdapter;
    private NewsProvider newsProvider;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        newsProvider = (NewsProvider) this.getActivity();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = view.findViewById(R.id.news_toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_filter) {
                    newsProvider.addFragment(new FiltersFragment(newsProvider.getFiltersList()));
                }
                return false;
            }
        });

        RecyclerView newsRv = view.findViewById(R.id.news_rv);
        newsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsRecyclerAdapter = new NewsRecyclerAdapter(newsProvider.getNewsList(), getContext());
        newsRv.setAdapter(newsRecyclerAdapter);
    }


    public void updateNewsList(List<News> updatedList) {
        newsRecyclerAdapter.updateNewsList(updatedList);
    }
}
