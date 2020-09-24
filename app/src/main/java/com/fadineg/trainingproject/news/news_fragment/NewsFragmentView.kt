package com.fadineg.trainingproject.news.news_fragment

import com.arellomobile.mvp.MvpView
import com.fadineg.trainingproject.news.model.News

interface NewsFragmentView : MvpView {
    fun updateNewsList(newNewsList: List<News>)
}