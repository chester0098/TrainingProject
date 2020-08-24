package com.fadineg.trainingproject.search;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.news.JsonInArray;
import com.fadineg.trainingproject.news.NewsProvider;
import com.fadineg.trainingproject.news.model.Articles;
import com.fadineg.trainingproject.news.model.News;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class EventsSearchVewPagerFragment extends Fragment {
    private Context context;
    private EventsRecyclerAdapter adapter;
    private ConstraintLayout plug;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        NewsProvider newsProvider = (NewsProvider) context;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_events_vew_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plug = view.findViewById(R.id.search_plug);

        JsonInArray jsonInArray = new JsonInArray();

        List<Articles> articlesList = new ArrayList<>();
        for (News news:jsonInArray.newsPars(context))
            articlesList.addAll(news.getArticles());

        RecyclerView rvSearch = view.findViewById(R.id.events_rv);
        rvSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new EventsRecyclerAdapter(articlesList);
        rvSearch.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SearchStringBus searchStringBus) {
        if (searchStringBus.getSearchString().equals(""))
            plug.setVisibility(View.VISIBLE);
        else {
            plug.setVisibility(View.GONE);
            adapter.filterResults(searchStringBus.getSearchString());
        }
    }

}
