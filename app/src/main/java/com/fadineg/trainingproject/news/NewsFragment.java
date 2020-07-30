package com.fadineg.trainingproject.news;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {
    private MainActivity mainActivity;
    private View view;
    NewsRecyclerAdapter newsRecyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mainActivity = (MainActivity) this.getActivity();
        Toolbar toolbar = view.findViewById(R.id.news_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_filter) {
                    mainActivity.addFragment(new FiltersFragment());
                }
                return false;
            }
        });
        RecyclerView newsRv = view.findViewById(R.id.news_rv);
        newsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsRecyclerAdapter = new NewsRecyclerAdapter(mainActivity.getNewsList(),getContext());
        newsRv.setAdapter(newsRecyclerAdapter);

        super.onViewCreated(view, savedInstanceState);
    }

    public void updateNewsList(){
        NewsDiffUtilCallback newsDiffUtilCallback =
                new NewsDiffUtilCallback(newsRecyclerAdapter.getNewsList(), mainActivity.getNewsList());
        DiffUtil.DiffResult newsDiffResult = DiffUtil.calculateDiff(newsDiffUtilCallback);

        newsRecyclerAdapter.setNewsList(mainActivity.getNewsList());
        newsDiffResult.dispatchUpdatesTo(newsRecyclerAdapter);
    }
}
