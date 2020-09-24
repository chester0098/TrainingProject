package com.fadineg.trainingproject.news.news_fragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.news.model.Articles
import com.fadineg.trainingproject.news.model.News
import java.util.*

@InjectViewState
class NewsFragmentPresenter : MvpPresenter<NewsFragmentView>() {
    fun getArticlesList(realmList: List<News>): List<Articles>? {
        val set: MutableSet<Articles> = mutableSetOf()
        for (news in realmList) {
            if (news.categorySwitch!!) news.articles?.let { set.addAll(it) }
        }
        return ArrayList(set)
    }
}