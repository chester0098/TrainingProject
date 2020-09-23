package com.fadineg.trainingproject.authorization

import android.content.Context
import android.content.Intent
import android.widget.EditText
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.main.MainActivity
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.observers.DisposableObserver

@InjectViewState
class AuthorizationPresenter : MvpPresenter<AuthorizationView>() {

    fun newInstance(context: Context): Intent? {
        return Intent(context, MainActivity::class.java)
    }

    fun emailPasswordObservable(email: EditText, password: EditText) {
        val emailObservable = RxEditText.editTextObservable(email)
        val passwordObservable = RxEditText.editTextObservable(password)
        val observable = Observable.combineLatest<String, String, Boolean>(emailObservable, passwordObservable,
                BiFunction { s, s2 -> s.length > 5 && s2.length > 5 })

        observable.subscribe(
                object : DisposableObserver<Boolean?>() {
                    override fun onNext(aBoolean: @NonNull Boolean?) {
                        if (aBoolean != null) {
                            viewState.updateButton(aBoolean)
                        }
                    }

                    override fun onError(e: @NonNull Throwable?) {}
                    override fun onComplete() {}
                })
    }
}

