package com.fadineg.trainingproject.news;

import android.content.Context;
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
import com.fadineg.trainingproject.news.eventBus.NewsBus;

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
                    newsProvider.setNewsList(filtersRecyclerAdapter.getNewsList());
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
        if (newsProvider.getNewsList().size() != 0) {

            /*Intent filtersServiceIntent = new Intent(getActivity(), FiltersService.class);
            context.startService(filtersServiceIntent);

            FiltersParsingTask filtersParsingTask = new FiltersParsingTask(context);
            filtersParsingTask.execute();

            executor.execute(filtersPars);*/

            /*RetrofitClient retrofitClient = new RetrofitClient();
            retrofitClient.downloadData();*/

            /*chooseCategoryTv.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);*/

            filtersRecyclerAdapter = new FiltersRecyclerAdapter(newsProvider.getNewsList(), context);
            filtersRv.setAdapter(filtersRecyclerAdapter);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NewsBus newsBus) {
        newsProvider.setNewsList(newsBus.getNewsList());

        filtersRecyclerAdapter = new FiltersRecyclerAdapter(newsProvider.getNewsList(), context);
        filtersRv.setAdapter(filtersRecyclerAdapter);

        chooseCategoryTv.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private Executor executor = (runnable) -> {
        new Thread(runnable).start();
    };
}
