package com.fadineg.trainingproject.news;

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
import com.fadineg.trainingproject.news.eventBus.NewsBus;
import com.fadineg.trainingproject.news.model.News;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class FiltersFragment extends Fragment {
    private RecyclerView filtersRv;
    private FiltersRecyclerAdapter filtersRecyclerAdapter;
    private List<News> newsList;

    public FiltersFragment(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filtersRecyclerAdapter = new FiltersRecyclerAdapter(newsList);

        filtersRv = view.findViewById(R.id.filters_rv);
        filtersRv.setLayoutManager(new LinearLayoutManager(getContext()));

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.filters_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_24dp);

        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        toolbar.setOnMenuItemClickListener((MenuItem item) -> {
            if (item.getItemId() == R.id.action_check) {
                getActivity().onBackPressed();
                EventBus.getDefault().post(new NewsBus(filtersRecyclerAdapter.getNewsList()));
            }
            return false;
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        filtersRv.setAdapter(filtersRecyclerAdapter);
    }
}
