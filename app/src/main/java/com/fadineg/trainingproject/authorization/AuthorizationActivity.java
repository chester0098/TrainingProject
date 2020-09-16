package com.fadineg.trainingproject.authorization;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.main.MainActivity;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class AuthorizationActivity extends AppCompatActivity {
    private EditText password;
    private EditText email;
    private Button join;

    private final static String EDIT_EMAIL = "EMail";
    private final static String EDIT_PASSWORD = "Password";

    private static Intent newInstance(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        password = findViewById(R.id.authorization_et_password);
        email = findViewById(R.id.authorization_et_email);

        join = findViewById(R.id.authorization_btn_join);
        join.setEnabled(false);
        join.setOnClickListener((View v) -> {
            startActivity(newInstance(getApplicationContext()));
                finish();
        });


        Observable<String> emailObservable = RxEditText.editTextObservable(email);
        Observable<String> passwordObservable = RxEditText.editTextObservable(password);

        Observable<Boolean> observable = Observable.combineLatest(emailObservable, passwordObservable, new BiFunction<String, String, Boolean>() {
            @Override
            public Boolean apply(String s, String s2) throws Throwable {
                return s.length() > 5 & s2.length() > 5;
            }
        });

        observable.subscribe(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(@NonNull Boolean aBoolean) {
                updateButton(aBoolean);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

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

    @Override
    protected void onSaveInstanceState(@androidx.annotation.NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EDIT_EMAIL, email.getText().toString());
        outState.putString(EDIT_PASSWORD, password.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@androidx.annotation.NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        email.setText(savedInstanceState.getString(EDIT_EMAIL));
        password.setText(savedInstanceState.getString(EDIT_PASSWORD));
    }
}
