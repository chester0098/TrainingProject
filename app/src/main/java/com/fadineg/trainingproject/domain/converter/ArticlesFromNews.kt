package com.fadineg.trainingproject.domain.converter

import com.fadineg.trainingproject.domain.model.news.Articles
import com.fadineg.trainingproject.domain.model.news.News

class ArticlesFromNews {
    fun getArticlesFromNews(newsList: MutableList<News>): MutableList<Articles> {
        val set: MutableSet<Articles> = mutableSetOf()
        for (news in newsList) {
            if (news.categorySwitch!!) news.articles?.let { set.addAll(it) }
        }
        return ArrayList(set)
    }
}