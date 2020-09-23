package com.fadineg.trainingproject.news.news_fragment;

import android.content.Context;
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
import com.fadineg.trainingproject.main.MainPresenter;
import com.fadineg.trainingproject.main.MainView;
import com.fadineg.trainingproject.news.model.News;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NewsFragment extends MvpAppCompatFragment implements NewsFragmentView {

    @InjectPresenter
    NewsFragmentPresenter newsFragmentPresenter;

    private MainView mainView;
    private RecyclerView newsRv;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newsFragmentPresenter.createAdapter((List<News>) getArguments().getSerializable(MainPresenter.NEWS_BUNDLE_KEY));

        Toolbar toolbar = view.findViewById(R.id.news_toolbar);
        newsRv = view.findViewById(R.id.news_rv);
        newsRv.setLayoutManager(new LinearLayoutManager(getContext()));

        toolbar.setOnMenuItemClickListener((MenuItem item) -> {
            if (item.getItemId() == R.id.action_filter) {
                mainView.openFilters();
            }
            return false;
        });

    }

    @Override
    public void setNewsRecyclerAdapter(NewsRecyclerAdapter newsRecyclerAdapter) {
        newsRv.setAdapter(newsRecyclerAdapter);
    }

    public void updateNewsList(List<News> newNewsList) {
        newsFragmentPresenter.updateNewsList(newNewsList);
    }

    @Override
    public void startDescriptionActivity(@NotNull Bundle bundle) {
        startActivity(newsFragmentPresenter.newInstance(getContext()).putExtras(bundle));
    }

}
