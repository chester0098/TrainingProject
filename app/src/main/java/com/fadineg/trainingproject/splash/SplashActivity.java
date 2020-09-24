package com.fadineg.trainingproject.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.authorization.AuthorizationActivity;

public class SplashActivity extends MvpAppCompatActivity implements SplashView {

    @InjectPresenter
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    private static Intent newInstance(Context context) {
        return new Intent(context, AuthorizationActivity.class);
    }

    @Override
    public void startActivity() {
        startActivity(newInstance(getApplicationContext()));
        finish();
    }
}
