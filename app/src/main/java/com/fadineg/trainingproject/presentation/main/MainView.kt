package com.fadineg.trainingproject.presentation.main

import android.graphics.Bitmap
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.fadineg.trainingproject.domain.model.news.News

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MainView : MvpView {
    fun openFilters()
    fun updateNewsFragment(newsList: List<News>)
    fun updateUserPhoto(userPhoto: Bitmap)
}