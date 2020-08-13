package com.fadineg.trainingproject.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.fadineg.trainingproject.R;
import com.google.android.material.tabs.TabLayout;

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
    private SearchViewPagerAdapter mFragmentAdapter;
    private String searchViewString = "";

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

        mFragmentAdapter = new SearchViewPagerAdapter(getChildFragmentManager(), getContext());

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

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                searchView.setQuery("", false);
                searchView.setQuery(searchViewString, false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        searchViewSubscribe();
        searchView.setQuery(searchViewString, false);

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
                        if (mViewPager.getCurrentItem() == 0) {
                            mFragmentAdapter.getPage1().updateRecyclerAdapter(searchViewString);
                        } else {
                            mFragmentAdapter.getPage2().updateRecyclerAdapter(searchViewString);
                        }
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
