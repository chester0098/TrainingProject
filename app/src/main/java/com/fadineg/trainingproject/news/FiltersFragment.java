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

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.news.asyncTask.FiltersParsingTask;
import com.fadineg.trainingproject.news.eventBus.FiltersBus;
import com.fadineg.trainingproject.news.intentService.FiltersService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

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
                    newsProvider.setFiltersList(filtersRecyclerAdapter.getFiltersList());
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
        if (newsProvider.getFiltersList().isEmpty()){

            Intent filtersServiceIntent = new Intent(getActivity(), FiltersService.class);
            context.startService(filtersServiceIntent);

            FiltersParsingTask filtersParsingTask = new FiltersParsingTask(context);
            filtersParsingTask.execute();

            executor.execute(filtersPars);

            chooseCategoryTv.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            filtersRecyclerAdapter = new FiltersRecyclerAdapter(newsProvider.getFiltersList(), context);
            filtersRv.setAdapter(filtersRecyclerAdapter);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FiltersBus filtersBus){
        newsProvider.setFiltersList(filtersBus.getFiltersList());

        filtersRecyclerAdapter = new FiltersRecyclerAdapter(newsProvider.getFiltersList(), context);
        filtersRv.setAdapter(filtersRecyclerAdapter);

        chooseCategoryTv.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private Runnable filtersPars = () -> {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JsonInArray jsonInArray = new JsonInArray();
        EventBus.getDefault().post(new FiltersBus(jsonInArray.filtersPars(context)));
    };

    private Executor executor = (runnable) -> {
        new Thread(runnable).start();
    };
}
