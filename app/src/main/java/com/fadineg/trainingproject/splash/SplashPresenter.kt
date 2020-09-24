package com.fadineg.trainingproject.splash

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class SplashPresenter() : MvpPresenter<SplashView>() {

    private val SPLASH_SCREEN_DURATION = 2000
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Handler().postDelayed({
            viewState.startActivity()
        }, SPLASH_SCREEN_DURATION.toLong())
    }
}