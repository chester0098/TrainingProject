package com.fadineg.trainingproject.presentation.news.filters_fragment;

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
import com.fadineg.trainingproject.domain.model.news.News;
import com.fadineg.trainingproject.presentation.main.MainActivity;

import java.util.List;


public class FiltersFragment extends MvpAppCompatFragment implements FiltersFragmentView {
    private RecyclerView filtersRv;

    @InjectPresenter
    FiltersFragmentPresenter filtersFragmentPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FiltersRecyclerAdapter filtersRecyclerAdapter =
                new FiltersRecyclerAdapter((List<News>) getArguments().getSerializable(MainActivity.NEWS_BUNDLE_KEY));

        filtersRv = view.findViewById(R.id.filters_rv);
        filtersRv.setLayoutManager(new LinearLayoutManager(getContext()));
        filtersRv.setAdapter(filtersRecyclerAdapter);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.filters_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_24dp);

        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        toolbar.setOnMenuItemClickListener((MenuItem item) -> {
            if (item.getItemId() == R.id.action_check) {
                getActivity().onBackPressed();
                filtersFragmentPresenter.postNewsList(filtersRecyclerAdapter.getNewsList());
            }
            return false;
        });
    }
}
