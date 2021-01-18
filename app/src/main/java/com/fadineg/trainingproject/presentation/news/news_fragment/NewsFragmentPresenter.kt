package com.fadineg.trainingproject.presentation.news.news_fragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.TrainingApplication
import com.fadineg.trainingproject.domain.converter.ArticlesFromNews
import com.fadineg.trainingproject.domain.model.news.Articles
import com.fadineg.trainingproject.domain.model.news.News
import javax.inject.Inject

@InjectViewState
class NewsFragmentPresenter : MvpPresenter<NewsFragmentView>() {
    init {
        TrainingApplication.appComponent.inject(this)
    }

    @Inject
    lateinit var articlesFromNews: ArticlesFromNews

    fun getArticlesList(realmList: MutableList<News>): MutableList<Articles> {
        return articlesFromNews.getArticlesFromNews(realmList)
    }
}