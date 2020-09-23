package com.fadineg.trainingproject.search.search_fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.fadineg.trainingproject.R;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

public class SearchFragment extends MvpAppCompatFragment implements SearchFragmentView {
    @InjectPresenter
    SearchFragmentPresenter searchFragmentPresenter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private SearchView mSearchView;

    private static final String SEARCH_STRING_KEY = "SearchString";

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(SEARCH_STRING_KEY, searchFragmentPresenter.getSearchViewString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            searchFragmentPresenter.setSearchViewString(savedInstanceState.getString(SEARCH_STRING_KEY));
        }


        SearchViewPagerAdapter mFragmentAdapter = new SearchViewPagerAdapter(getChildFragmentManager(), getContext());

        mViewPager = view.findViewById(R.id.search_viewPager);
        mTabLayout = view.findViewById(R.id.search_tabLayout);


        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        mSearchView = view.findViewById(R.id.searchView);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setIconified(false);
        mSearchView.clearFocus();


        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void onResume() {
        super.onResume();
        searchFragmentPresenter.searchViewSubscribe(mSearchView);
        mSearchView.setQuery(searchFragmentPresenter.getSearchViewString(), false);
        searchFragmentPresenter.eventBusPost();
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
