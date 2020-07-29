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

public class NewsFragment extends Fragment {

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        MainActivity ma = (MainActivity) this.getActivity();
        Toolbar toolbar = view.findViewById(R.id.news_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_filter) {
                    ma.addFragment(new FiltersFragment());
                }
                return false;
            }
        });

        List<News> newsList = new ArrayList<>();
        newsList.add(new News(R.drawable.img_child, "Спонсоры отремонтируют школу-интернат",
                "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …"));
        newsList.add(new News(R.drawable.image_man, "Конкурс по вокальному пению в детском доме №6",
                "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …"));
        newsList.add(new News(R.drawable.img_man, "Спонсоры отремонтируют школу-интернат",
                "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …"));
        RecyclerView newsRv = view.findViewById(R.id.news_rv);
        newsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        NewsRecyclerAdapter newsRecyclerAdapter = new NewsRecyclerAdapter(newsList);
        newsRv.setAdapter(newsRecyclerAdapter);
        super.onViewCreated(view, savedInstanceState);
    }
}
