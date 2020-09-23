package com.fadineg.trainingproject.authorization

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface AuthorizationView : MvpView {
    fun setObservable()
    fun updateButton(valid: Boolean)
}