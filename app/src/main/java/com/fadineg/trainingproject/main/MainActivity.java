package com.fadineg.trainingproject.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.fadineg.trainingproject.help.HelpFragment;
import com.fadineg.trainingproject.profile.ProfileFragment;
import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    static final int REQUEST_TAKE_PHOTO = 1;
    private BottomNavigationView bottomNavigationView;
    ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.bnv_help);
        bottomNavigationView.getMenu().findItem(R.id.bnv_help).setChecked(true);

        profileFragment = new ProfileFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        bottomNavigationView.getMenu().findItem(item.getItemId()).setChecked(true);
        switch (item.getItemId()) {
            case R.id.bnv_profile:
                loadFragment(profileFragment);
                break;
            case R.id.bnv_history:
                //будет реализовано позднее
                break;
            case R.id.bnv_help:
                loadFragment(new HelpFragment());
                break;
            case R.id.bnv_search:
                loadFragment(new SearchFragment());
                break;
            case R.id.bnv_news:
                //будет реализовано позднее
                break;
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            String _path = getExternalFilesDir("Pictures") + File.separator + "temp.jpg";
            Bitmap mBitmap = BitmapFactory.decodeFile(_path);
            profileFragment.setImageFromCamera(mBitmap);
        }
    }

}
