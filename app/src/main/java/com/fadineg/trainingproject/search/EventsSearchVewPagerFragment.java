package com.fadineg.trainingproject.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fadineg.trainingproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventsSearchVewPagerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_events_vew_pager, container, false);
        RecyclerView rvSearch = view.findViewById(R.id.events_rv);
        rvSearch.setHasFixedSize(true);
        rvSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        EventsRecyclerAdapter adapter = new EventsRecyclerAdapter(createdEventsList());
        rvSearch.setAdapter(adapter);
        return view;
    }


    private List<String> createdEventsList() {
        List<String> eventsList = new ArrayList<>();
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int length;
        StringBuilder sb;
        char c;
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            length = (int) (5 + (Math.random() * 15));
            sb = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            eventsList.add(sb.toString());
        }
        return eventsList;
    }
}
