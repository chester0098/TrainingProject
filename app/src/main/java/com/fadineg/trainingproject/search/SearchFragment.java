package com.fadineg.trainingproject.search;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fadineg.trainingproject.R;
import com.google.android.material.tabs.TabLayout;

public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        SearchViewPagerAdapter mFragmentAdapter = new SearchViewPagerAdapter(getChildFragmentManager(), getContext());
        ViewPager mViewPager = view.findViewById(R.id.search_viewPager);
        TabLayout mTabLayout = view.findViewById(R.id.search_tabLayout);

        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(false);
        searchView.setIconified(false);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        return view;
    }

}
