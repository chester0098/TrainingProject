package com.fadineg.trainingproject.news;

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

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class FiltersFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity = (MainActivity) this.getActivity();
        List<Filters> filters = new ArrayList<>(mainActivity.getFiltersList());

        RecyclerView recyclerView = view.findViewById(R.id.filters_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FiltersRecyclerAdapter filtersRecyclerAdapter = new FiltersRecyclerAdapter(filters, getContext());
        recyclerView.setAdapter(filtersRecyclerAdapter);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.filters_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_24dp);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_check) {
                    mainActivity.setFiltersList(filtersRecyclerAdapter.getFiltersList());
                    mainActivity.updateNewsAdapter();
                    getActivity().onBackPressed();
                }
                return false;
            }
        });

    }
}
