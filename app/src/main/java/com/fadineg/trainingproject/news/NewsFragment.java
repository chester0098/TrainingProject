package com.fadineg.trainingproject.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.news.asyncTask.NewsParsingTask;
import com.fadineg.trainingproject.news.eventBus.NewsBus;
import com.fadineg.trainingproject.news.intentService.NewsService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.Executor;

public class NewsFragment extends Fragment {
    private NewsRecyclerAdapter newsRecyclerAdapter;
    private NewsProvider newsProvider;
    private RecyclerView newsRv;
    private ProgressBar progressBar;
    private Context context;

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

        if (newsProvider.getNewsList().isEmpty()){

            NewsParsingTask jsonParsingTask = new NewsParsingTask(context);
            jsonParsingTask.execute();

            Intent newsServiceIntent = new Intent(getActivity(), NewsService.class);
            context.startService(newsServiceIntent);

            executor.execute(newsPars);

            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            newsRv.setLayoutManager(new LinearLayoutManager(context));
            newsRecyclerAdapter = new NewsRecyclerAdapter(newsProvider.getNewsList(), context);
            newsRv.setAdapter(newsRecyclerAdapter);
        }
    }

    public void updateNewsList(List<News> updatedList) {
        newsRecyclerAdapter.updateNewsList(updatedList);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NewsBus newsBus){
        newsProvider.setNewsList(newsBus.getNewsList());

        newsRv.setLayoutManager(new LinearLayoutManager(context));
        newsRecyclerAdapter = new NewsRecyclerAdapter(newsProvider.getNewsList(), getContext());
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
}
