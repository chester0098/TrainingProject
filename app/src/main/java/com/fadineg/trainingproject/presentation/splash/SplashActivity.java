package com.fadineg.trainingproject.presentation.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.presentation.authorization.AuthorizationActivity;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_SCREEN_DURATION = 2000;

    private static Intent newInstance(Context context) {
        return new Intent(context, AuthorizationActivity.class);
    }

    public void startActivity() {
        startActivity(newInstance(getApplicationContext()));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(newInstance(getApplicationContext()));
                finish();
            }
        }, SPLASH_SCREEN_DURATION);
    }
}
