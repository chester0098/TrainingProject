package com.fadineg.trainingproject.search;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

public class SearchViewPagerAdapter extends FragmentPagerAdapter {

    SearchViewPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        return new EventsSearchVewPagerFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "По мероприятиям";

            case 1:
                return "По НКО";

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

}
