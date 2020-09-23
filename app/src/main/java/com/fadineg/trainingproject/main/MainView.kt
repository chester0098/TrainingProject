package com.fadineg.trainingproject.main

import android.graphics.Bitmap
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.fadineg.trainingproject.news.model.News

@StateStrategyType(value = OneExecutionStateStrategy::class)
interface MainView : MvpView {
    fun openFilters()
    fun updateNewsFragment(newsList: List<News>)
    fun replaceFragment(fragment: Fragment, fragmentTag: String)
    fun addFragment(fragment: Fragment, fragmentTag: String)
    fun changeUserPhoto(userPhoto: Bitmap)
}