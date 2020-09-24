package com.fadineg.trainingproject.authorization;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.fadineg.trainingproject.R;
import com.fadineg.trainingproject.main.MainActivity;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class AuthorizationActivity extends MvpAppCompatActivity implements AuthorizationView {
    EditText password;
    EditText email;
    Button join;

    @InjectPresenter
    AuthorizationPresenter authorizationPresenter;

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

        subscribe();

        join.setEnabled(false);
        join.setOnClickListener((View v) -> {
            startActivity(newInstance(getApplicationContext()));
            finish();
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
    public void subscribe() {
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
}
