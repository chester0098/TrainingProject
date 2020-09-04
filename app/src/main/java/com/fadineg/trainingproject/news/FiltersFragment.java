package com.fadineg.trainingproject.news;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.news.eventBus.NewsBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class FiltersFragment extends Fragment {
    private NewsProvider newsProvider;
    private RecyclerView filtersRv;
    private FiltersRecyclerAdapter filtersRecyclerAdapter;
    private ProgressBar progressBar;
    private Context context;
    private TextView chooseCategoryTv;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.filters_progressBar);
        progressBar.setVisibility(View.GONE);

        chooseCategoryTv = view.findViewById(R.id.filters_tv_chooseCategory);

        filtersRv = view.findViewById(R.id.filters_rv);
        filtersRv.setLayoutManager(new LinearLayoutManager(context));

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.filters_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_24dp);

        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_check) {
                    newsProvider.getRealmManager().setNewsInRealm(filtersRecyclerAdapter.getNewsList());
                    newsProvider.updateNewsAdapter();
                    getActivity().onBackPressed();
                }
                return false;
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!NewsFragment.firstLoad) {
            filtersRecyclerAdapter = new FiltersRecyclerAdapter(newsProvider.getRealmManager().getNewsFromRealm(), context);
            filtersRv.setAdapter(filtersRecyclerAdapter);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NewsBus newsBus) {
        NewsFragment.firstLoad = false;

        newsProvider.getRealmManager().setNewsInRealm(newsBus.getNewsList());

        filtersRecyclerAdapter = new FiltersRecyclerAdapter(newsProvider.getRealmManager().getNewsFromRealm(), context);
        filtersRv.setAdapter(filtersRecyclerAdapter);

        chooseCategoryTv.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
