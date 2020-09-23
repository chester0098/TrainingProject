package com.fadineg.trainingproject.splash

import android.content.Context
import android.content.Intent
import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.authorization.AuthorizationActivity

@InjectViewState
class SplashPresenter() : MvpPresenter<SplashView>() {

    private val SPLASH_SCREEN_DURATION = 2000
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Handler().postDelayed({
            viewState.startActivity()
        }, SPLASH_SCREEN_DURATION.toLong())
    }

    fun newInstance(context: Context): Intent? {
        return Intent(context, AuthorizationActivity::class.java)
    }
}