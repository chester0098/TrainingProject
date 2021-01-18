package com.fadineg.trainingproject.domain.repositoryInterface

import com.fadineg.trainingproject.domain.model.news.News

interface RealmRepositoryInterface {
    fun setNewsInRealm(news: List<News>)

    fun getNewsFromRealm(): List<News>?
}