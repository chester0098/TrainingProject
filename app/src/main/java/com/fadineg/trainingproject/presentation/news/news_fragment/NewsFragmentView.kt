package com.fadineg.trainingproject.presentation.news.news_fragment

import com.arellomobile.mvp.MvpView
import com.fadineg.trainingproject.domain.model.news.News

interface NewsFragmentView : MvpView {
    fun updateNewsList(newNewsList: List<News>)
}