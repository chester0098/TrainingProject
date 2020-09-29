package com.fadineg.trainingproject.presentation.splash

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = SkipStrategy::class)
interface SplashView : MvpView {
    fun startActivity()
}