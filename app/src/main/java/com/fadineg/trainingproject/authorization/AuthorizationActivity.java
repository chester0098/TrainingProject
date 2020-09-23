package com.fadineg.trainingproject.authorization;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.fadineg.trainingproject.R;

public class AuthorizationActivity extends MvpAppCompatActivity implements AuthorizationView {
    EditText password;
    EditText email;
    Button join;

    @InjectPresenter
    AuthorizationPresenter authorizationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        password = findViewById(R.id.authorization_et_password);
        email = findViewById(R.id.authorization_et_email);
        join = findViewById(R.id.authorization_btn_join);

        setObservable();

        join.setEnabled(false);
        join.setOnClickListener((View v) -> {
            startActivity(authorizationPresenter.newInstance(getApplicationContext()));
            finish();
        });
    }

    @Override
    public void setObservable() {
        authorizationPresenter.emailPasswordObservable(email, password);
    }

    @Override
    public void updateButton(boolean valid) {
        if (valid) {
            join.setEnabled(true);
            join.setClickable(true);
            join.setBackgroundResource(R.drawable.btn_background);
        } else {
            join.setClickable(false);
            join.setEnabled(false);
            join.setBackgroundResource(R.drawable.btn_inactive_background);
        }
    }
}
