package com.fadineg.trainingproject.news;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.main.MainActivity;
import com.fadineg.trainingproject.news.eventBus.NewsBus;
import com.fadineg.trainingproject.news.model.Articles;
import com.fadineg.trainingproject.news.model.News;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

public class NewsFragment extends Fragment {
    private NewsRecyclerAdapter newsRecyclerAdapter;
    private NewsProvider newsProvider;
    private RecyclerView newsRv;
    private List<News> newsList;
    private Runnable newsPars = () -> {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JsonInArray jsonInArray = new JsonInArray();
        EventBus.getDefault().post(new NewsBus(jsonInArray.newsPars(getContext())));
    };

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
        newsProvider = (NewsProvider) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newsList = (List<News>) getArguments().getSerializable(MainActivity.NEWS_BUNDLE_KEY);

        newsRecyclerAdapter = new NewsRecyclerAdapter(getArticlesList(newsList), getContext());

        Toolbar toolbar = view.findViewById(R.id.news_toolbar);
        newsRv = view.findViewById(R.id.news_rv);
        newsRv.setLayoutManager(new LinearLayoutManager(getContext()));

        toolbar.setOnMenuItemClickListener((MenuItem item) -> {
            if (item.getItemId() == R.id.action_filter) {
                newsProvider.openFilters();
            }
            return false;
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        newsRv.setAdapter(newsRecyclerAdapter);
    }

    public void updateNewsList(List<News> newNewsList) {
        newsRecyclerAdapter.updateNewsList(getArticlesList(newNewsList));
    }

    private Executor executor = (runnable) -> {
        new Thread(runnable).start();
    };

    private List<Articles> getArticlesList(List<News> realmList) {
        Set<Articles> set = new LinkedHashSet<>();
        for (News news : realmList) {
            if (news.getCategorySwitch())
                set.addAll(news.getArticles());
        }
        return new ArrayList<>(set);
    }
}
