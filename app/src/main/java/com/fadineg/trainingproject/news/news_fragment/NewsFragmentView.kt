package com.fadineg.trainingproject.news.news_fragment

import android.os.Bundle
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface NewsFragmentView : MvpView {
    fun setNewsRecyclerAdapter(newsRecyclerAdapter: NewsRecyclerAdapter?)

    @StateStrategyType(value = SkipStrategy::class)
    fun startDescriptionActivity(bundle: Bundle)
}