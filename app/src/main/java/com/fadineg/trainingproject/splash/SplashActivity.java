package com.fadineg.trainingproject.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.authorization.AuthorizationActivity;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_SCREEN_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, AuthorizationActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_DURATION);
    }
}
