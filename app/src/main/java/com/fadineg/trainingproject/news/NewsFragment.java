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
import android.widget.ProgressBar;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.news.eventBus.NewsBus;
import com.fadineg.trainingproject.news.model.Articles;
import com.fadineg.trainingproject.news.model.News;
import com.fadineg.trainingproject.news.retrofit.RetrofitClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

import io.realm.RealmResults;

public class NewsFragment extends Fragment {
    private NewsRecyclerAdapter newsRecyclerAdapter;
    private NewsProvider newsProvider;
    private RecyclerView newsRv;
    private ProgressBar progressBar;
    private Context context;
    static Boolean fistLoad = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null)
            fistLoad = false;
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        newsProvider = (NewsProvider) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = view.findViewById(R.id.news_toolbar);
        newsRv = view.findViewById(R.id.news_rv);

        progressBar = view.findViewById(R.id.news_progressBar);
        progressBar.setVisibility(View.GONE);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_filter) {
                    newsProvider.openFilters();
                }
                return false;
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        if (fistLoad) {
            RetrofitClient retrofitClient = new RetrofitClient(newsProvider.getRealm());
            retrofitClient.downloadData(context);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            RealmResults realmResults = newsProvider.getRealm().where(News.class).findAllAsync();
            newsRv.setLayoutManager(new LinearLayoutManager(context));
            newsRecyclerAdapter = new NewsRecyclerAdapter(getArticlesList(new ArrayList<>(realmResults)), context);
            newsRv.setAdapter(newsRecyclerAdapter);
        }
    }

    public void updateNewsList() {
        RealmResults realmResults = newsProvider.getRealm().where(News.class).findAllAsync();
        newsRecyclerAdapter.updateNewsList(getArticlesList(new ArrayList<>(realmResults)));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NewsBus newsBus) {

        fistLoad = false;

        newsRv.setLayoutManager(new LinearLayoutManager(context));
        newsRecyclerAdapter = new NewsRecyclerAdapter(getArticlesList(newsBus.getNewsList()), getContext());
        newsRv.setAdapter(newsRecyclerAdapter);

        progressBar.setVisibility(View.GONE);
    }

    private Runnable newsPars = () -> {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JsonInArray jsonInArray = new JsonInArray();
        EventBus.getDefault().post(new NewsBus(jsonInArray.newsPars(context)));
    };

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
