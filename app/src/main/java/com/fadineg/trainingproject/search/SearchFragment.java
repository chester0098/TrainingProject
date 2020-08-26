package com.fadineg.trainingproject.search;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.news.NewsProvider;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchFragment extends Fragment {
    private SearchView searchView;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private String searchViewString = "";
    NewsProvider newsProvider;
    private Context context;

    private static final String SEARCH_STRING_KEY = "SearchString";

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(SEARCH_STRING_KEY, searchViewString);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            searchViewString = savedInstanceState.getString(SEARCH_STRING_KEY);
        }


        SearchViewPagerAdapter mFragmentAdapter = new SearchViewPagerAdapter(getChildFragmentManager(), getContext());

        mViewPager = view.findViewById(R.id.search_viewPager);
        mTabLayout = view.findViewById(R.id.search_tabLayout);


        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = view.findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
        searchView.clearFocus();


        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onResume() {
        super.onResume();
        searchViewSubscribe();
        searchView.setQuery(searchViewString, false);
        EventBus.getDefault().post(new SearchStringBus(searchViewString));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void searchViewSubscribe() {
        Observable<String> searchViewObservable = RxSearchView.searchViewObservable(searchView);

        searchViewObservable.subscribeOn(Schedulers.io())
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull String s) {
                        searchViewString = s;
                        EventBus.getDefault().post(new SearchStringBus(searchViewString));
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
