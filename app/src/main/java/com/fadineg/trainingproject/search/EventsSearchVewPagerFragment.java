package com.fadineg.trainingproject.search;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.news.JsonInArray;
import com.fadineg.trainingproject.news.NewsProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.core.Observable;

public class EventsSearchVewPagerFragment extends Fragment {
    private Context context;
    private EventsRecyclerAdapter adapter;
    private ConstraintLayout plug;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
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

        RecyclerView rvSearch = view.findViewById(R.id.events_rv);
        rvSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new EventsRecyclerAdapter(jsonInArray.newsPars(context));
        rvSearch.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    void updateRecyclerAdapter(String newString) {
        if (newString.equals(""))
            plug.setVisibility(View.VISIBLE);
        else {
            plug.setVisibility(View.GONE);
            adapter.getFilteredResults(newString);
        }
    }

}
