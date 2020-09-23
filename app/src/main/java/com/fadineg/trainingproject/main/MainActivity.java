package com.fadineg.trainingproject.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.news.NewsProvider;
import com.fadineg.trainingproject.news.model.News;
import com.fadineg.trainingproject.news.news_fragment.NewsFragment;
import com.fadineg.trainingproject.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NewsProvider, MainView {

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
        mainPresenter.onNavigationItemSelected(item.getItemId());
        return false;
    }

    @Override
    public void openFilters() {
        mainPresenter.openFilters();
    }

    @Override
    public void replaceFragment(@NotNull Fragment fragment, @NotNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, fragment, fragmentTag)
                .commit();
    }

    @Override
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
        mainPresenter.onActivityResult(requestCode, data, getApplicationContext());
    }

    @Override
    public void changeUserPhoto(Bitmap userPhoto) {
        Fragment profileFragment = getSupportFragmentManager().findFragmentByTag(MainPresenter.PROFILE_FRAGMENT_TAG);
        if (profileFragment != null) {
            ((ProfileFragment) profileFragment).setUserPhoto(userPhoto);
        }
    }

    @Override
    public void updateNewsFragment(@NotNull List<? extends News> newsList) {
        Fragment newsFragment = getSupportFragmentManager().findFragmentByTag(MainPresenter.NEWS_FRAGMENT_TAG);
        if (newsFragment != null && newsFragment.isVisible()) {
            ((NewsFragment) newsFragment).updateNewsList((List<News>) newsList);
        }
    }
}
