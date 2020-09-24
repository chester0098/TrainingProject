package com.fadineg.trainingproject.news.news_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.main.MainActivity;
import com.fadineg.trainingproject.main.MainView;
import com.fadineg.trainingproject.news.NewsDescriptionActivity;
import com.fadineg.trainingproject.news.model.News;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NewsFragment extends MvpAppCompatFragment implements NewsFragmentView, ItemClickListener {

    @InjectPresenter
    NewsFragmentPresenter newsFragmentPresenter;

    private MainView mainView;
    private NewsRecyclerAdapter newsRecyclerAdapter;

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
        mainView = (MainView) context;
    }

    private static Intent newInstance(Context context) {
        return new Intent(context, NewsDescriptionActivity.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ItemClickListener itemClickListener = this;
        List<News> newsList = (List<News>) getArguments().getSerializable(MainActivity.NEWS_BUNDLE_KEY);
        newsRecyclerAdapter = new NewsRecyclerAdapter(newsFragmentPresenter.getArticlesList(newsList), itemClickListener);

        Toolbar toolbar = view.findViewById(R.id.news_toolbar);
        RecyclerView newsRv = view.findViewById(R.id.news_rv);
        newsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        newsRv.setAdapter(newsRecyclerAdapter);

        toolbar.setOnMenuItemClickListener((MenuItem item) -> {
            if (item.getItemId() == R.id.action_filter) {
                mainView.openFilters();
            }
            return false;
        });

    }

    @Override
    public void updateNewsList(@NotNull List<? extends News> newNewsList) {
        newsRecyclerAdapter.updateNewsList(newsFragmentPresenter.getArticlesList(newNewsList));
    }

    @Override
    public void onItemClick(@NotNull Bundle bundle) {
        startActivity(newInstance(getContext()).putExtras(bundle));
    }
}
