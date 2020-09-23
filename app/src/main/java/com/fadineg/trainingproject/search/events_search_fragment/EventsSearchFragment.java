package com.fadineg.trainingproject.search.events_search_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.fadineg.trainingproject.R;

public class EventsSearchFragment extends MvpAppCompatFragment implements EventsSearchFragmentView {
    @InjectPresenter
    EventsSearchFragmentPresenter eventsSearchFragmentPresenter;
    private ConstraintLayout plug;
    private RecyclerView rvSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_events_vew_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plug = view.findViewById(R.id.search_plug);

        rvSearch = view.findViewById(R.id.events_rv);
        rvSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void setSearchRecyclerView(EventsRecyclerAdapter eventsRecyclerAdapter) {
        rvSearch.setAdapter(eventsRecyclerAdapter);
    }

    @Override
    public void setPlugVisible() {
        plug.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPlugGone() {
        plug.setVisibility(View.GONE);
    }

}
