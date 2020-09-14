package com.fadineg.trainingproject.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.RealmManager;
import com.fadineg.trainingproject.help.HelpFragment;
import com.fadineg.trainingproject.news.FiltersFragment;
import com.fadineg.trainingproject.news.NewsFragment;
import com.fadineg.trainingproject.news.NewsProvider;
import com.fadineg.trainingproject.news.eventBus.NewsBus;
import com.fadineg.trainingproject.news.retrofit.RetrofitClient;
import com.fadineg.trainingproject.profile.ProfileFragment;
import com.fadineg.trainingproject.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NewsProvider {
    public static final int REQUEST_TAKE_PHOTO = 1;
    public static final int REQUEST_CHOOSE_PHOTO = 2;
    public static final String FILES_DIR = "Pictures";
    public static final String FILE_NAME = "temp.jpg";
    public static final String ITEM_ID_KEY = "ItemId";
    public final String PROFILE_FRAGMENT_TAG = "profile";
    public final String NEWS_FRAGMENT_TAG = "news";
    public final String SEARCH_FRAGMENT_TAG = "search";
    public final String HELP_FRAGMENT_TAG = "help";

    private BottomNavigationView bottomNavigationView;
    private FragmentTransaction fragmentTransaction;

    private RealmManager realmManager;

    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidThreeTen.init(this);

        realmManager = new RealmManager(this);
        realmManager.createInstance();

        RetrofitClient retrofitClient = new RetrofitClient();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            retrofitClient.downloadData(this);

            bottomNavigationView.setSelectedItemId(R.id.bnv_help);
            bottomNavigationView.getMenu().findItem(R.id.bnv_help).setChecked(true);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(NewsBus newsBus) {
        realmManager.setNewsInRealm(newsBus.getNewsList());

        Fragment newsFragment = getSupportFragmentManager().findFragmentByTag(NEWS_FRAGMENT_TAG);
        if (newsFragment != null && newsFragment.isVisible()) {
            ((NewsFragment) newsFragment).updateNewsList(newsBus.getNewsList());
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        itemId = item.getItemId();
        bottomNavigationView.getMenu().findItem(item.getItemId()).setChecked(true);
        switch (item.getItemId()) {
            case R.id.bnv_profile:
                loadFragment(new ProfileFragment(), PROFILE_FRAGMENT_TAG);
                break;
            case R.id.bnv_history:
                //будет реализовано позднее
                break;
            case R.id.bnv_help:
                loadFragment(new HelpFragment(), HELP_FRAGMENT_TAG);
                break;
            case R.id.bnv_search:
                loadFragment(new SearchFragment(), SEARCH_FRAGMENT_TAG);
                break;
            case R.id.bnv_news:
                loadFragment(new NewsFragment(realmManager.getNewsFromRealm()), NEWS_FRAGMENT_TAG);
                break;
        }
        return false;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(ITEM_ID_KEY, itemId);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        itemId = savedInstanceState.getInt(ITEM_ID_KEY);
        bottomNavigationView.getMenu().findItem(itemId).setChecked(true);
    }

    public void loadFragment(Fragment fragment, String fragmentTag) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_content, fragment, fragmentTag);
        fragmentTransaction.commit();
    }

    @Override
    public void openFilters() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_content, new FiltersFragment(realmManager.getNewsFromRealm())).addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            String path = getExternalFilesDir(FILES_DIR) + File.separator + FILE_NAME;
            Bitmap takenImage = BitmapFactory.decodeFile(path);
            changeUserPhoto(takenImage);
        } else if (requestCode == REQUEST_CHOOSE_PHOTO) {
            try {
                Uri imageUri = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(imageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                changeUserPhoto(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void changeUserPhoto(Bitmap userPhoto) {
        Fragment profileFragment = getSupportFragmentManager().findFragmentByTag(PROFILE_FRAGMENT_TAG);
        if (profileFragment != null) {
            ((ProfileFragment) profileFragment).setUserPhoto(userPhoto);
        }
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
    protected void onDestroy() {
        super.onDestroy();
        realmManager.closeRealm();
    }
}
