package com.fadineg.trainingproject.domain.rxjava;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class RxEditText {
    public static Observable<String> editTextObservable(@NonNull EditText editText) {

        return Observable.create(emitter -> {

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    emitter.onNext(s.toString());
                }
            });
        });
    }
}
