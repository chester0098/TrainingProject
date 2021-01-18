package com.fadineg.trainingproject.presentation.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.domain.model.news.News;
import com.fadineg.trainingproject.presentation.help.HelpFragment;
import com.fadineg.trainingproject.presentation.news.filters_fragment.FiltersFragment;
import com.fadineg.trainingproject.presentation.news.news_fragment.NewsFragment;
import com.fadineg.trainingproject.presentation.profile.profile_fragment.ProfileFragment;
import com.fadineg.trainingproject.presentation.search.search_fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;


public class MainActivity extends MvpAppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, MainView {
    public static final int REQUEST_TAKE_PHOTO = 1;
    public static final int REQUEST_CHOOSE_PHOTO = 2;
    public static final String NEWS_BUNDLE_KEY = "newsList";
    public final String PROFILE_FRAGMENT_TAG = "profile";
    public final String NEWS_FRAGMENT_TAG = "news";
    public final String SEARCH_FRAGMENT_TAG = "search";
    public final String HELP_FRAGMENT_TAG = "help";
    public final String FILTERS_FRAGMENT_TAG = "filters";


    @InjectPresenter
    MainPresenter mainPresenter;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            mainPresenter.downloadData(getApplicationContext());
            bottomNavigationView.setSelectedItemId(R.id.bnv_help);
            bottomNavigationView.getMenu().findItem(R.id.bnv_help).setChecked(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        bottomNavigationView.getMenu().findItem(item.getItemId()).setChecked(true);
        switch (item.getItemId()) {
            case R.id.bnv_profile:
                replaceFragment(new ProfileFragment(), PROFILE_FRAGMENT_TAG);
                break;
            case R.id.bnv_history:
                //будет реализовано позднее
                break;
            case R.id.bnv_help:
                replaceFragment(new HelpFragment(), HELP_FRAGMENT_TAG);
                break;
            case R.id.bnv_search:
                replaceFragment(new SearchFragment(), SEARCH_FRAGMENT_TAG);
                break;
            case R.id.bnv_news:
                NewsFragment newsFragment = new NewsFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(NEWS_BUNDLE_KEY, (Serializable) mainPresenter.getNews());
                newsFragment.setArguments(bundle);

                replaceFragment(newsFragment, NEWS_FRAGMENT_TAG);
                break;
        }
        return false;
    }

    @Override
    public void openFilters() {
        FiltersFragment filtersFragment = new FiltersFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(NEWS_BUNDLE_KEY, (Serializable) mainPresenter.getNews());
        filtersFragment.setArguments(bundle);
        addFragment(filtersFragment, FILTERS_FRAGMENT_TAG);
    }

    public void replaceFragment(@NotNull Fragment fragment, @NotNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, fragment, fragmentTag)
                .commit();
    }

    public void addFragment(Fragment fragment, String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_content, fragment, fragmentTag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            mainPresenter.takePhoto(data, getApplicationContext());
        } else if (requestCode == REQUEST_CHOOSE_PHOTO) {
            mainPresenter.choosePhoto(data, getApplicationContext());
        }
    }

    @Override
    public void updateUserPhoto(@NotNull Bitmap userPhoto) {
        Fragment profileFragment = getSupportFragmentManager().findFragmentByTag(PROFILE_FRAGMENT_TAG);
        if (profileFragment != null) {
            ((ProfileFragment) profileFragment).setUserPhoto(userPhoto);
        }
    }

    @Override
    public void updateNewsFragment(@NotNull List<? extends News> newsList) {
        Fragment newsFragment = getSupportFragmentManager().findFragmentByTag(NEWS_FRAGMENT_TAG);
        if (newsFragment != null && newsFragment.isVisible()) {
            ((NewsFragment) newsFragment).updateNewsList((List<News>) newsList);
        }
    }
}
