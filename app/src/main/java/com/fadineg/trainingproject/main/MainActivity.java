package com.fadineg.trainingproject.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.fadineg.trainingproject.RealmManager;
import com.fadineg.trainingproject.help.HelpFragment;
import com.fadineg.trainingproject.news.FiltersFragment;
import com.fadineg.trainingproject.news.NewsFragment;
import com.fadineg.trainingproject.news.NewsProvider;
import com.fadineg.trainingproject.profile.ProfileFragment;
import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NewsProvider {
    public static final int REQUEST_TAKE_PHOTO = 1;
    public static final int REQUEST_CHOOSE_PHOTO = 2;
    public static final String FILES_DIR = "Pictures";
    public static final String FILE_NAME = "temp.jpg";
    public static final String ITEM_ID_KEY = "ItemId";

    private BottomNavigationView bottomNavigationView;
    private ProfileFragment profileFragment;
    private HelpFragment helpFragment;
    private SearchFragment searchFragment;
    private NewsFragment newsFragment;

    private RealmManager realmManager;

    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realmManager = new RealmManager();
        realmManager.setInstance();

        profileFragment = new ProfileFragment();
        helpFragment = new HelpFragment();
        searchFragment = new SearchFragment();
        newsFragment = new NewsFragment();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.bnv_help);
            bottomNavigationView.getMenu().findItem(R.id.bnv_help).setChecked(true);
        }

    }

    @Override
    public Realm getRealm() {
        return realmManager.getRealm();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        itemId = item.getItemId();
        bottomNavigationView.getMenu().findItem(item.getItemId()).setChecked(true);
        switch (item.getItemId()) {
            case R.id.bnv_profile:
                loadFragment(profileFragment);
                break;
            case R.id.bnv_history:
                //будет реализовано позднее
                break;
            case R.id.bnv_help:
                loadFragment(helpFragment);
                break;
            case R.id.bnv_search:
                loadFragment(searchFragment);
                break;
            case R.id.bnv_news:
                loadFragment(newsFragment);
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

    public void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
    }

    @Override
    public void openFilters() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, new FiltersFragment()).addToBackStack(null);
        ft.commit();
    }

    @Override
    public void updateNewsAdapter() {
        newsFragment.updateNewsList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            String path = getExternalFilesDir(FILES_DIR) + File.separator + FILE_NAME;
            Bitmap takenImage = BitmapFactory.decodeFile(path);
            profileFragment.setImageFromCamera(takenImage);
        } else if (requestCode == REQUEST_CHOOSE_PHOTO) {
            try {
                Uri imageUri = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(imageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                profileFragment.setImageFromCamera(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmManager.closeRealm();
    }
}
