package com.fadineg.trainingproject.search;

import android.content.Context;
import android.os.Parcelable;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fadineg.trainingproject.R;

import org.jetbrains.annotations.NotNull;


public class SearchViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private EventsSearchVewPagerFragment page1;
    private EventsSearchVewPagerFragment page2;


    @Override public Parcelable saveState() {
        return null;
    }

    SearchViewPagerAdapter(FragmentManager supportFragmentManager, Context context) {
        super(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        page1 = new EventsSearchVewPagerFragment();
        page2 = new EventsSearchVewPagerFragment();
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return page1;
            case 1:
                return page2;
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.pageTitle1);

            case 1:
                return context.getString(R.string.pageTitle2);

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }


}
