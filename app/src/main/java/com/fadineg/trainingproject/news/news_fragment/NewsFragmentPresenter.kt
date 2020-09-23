package com.fadineg.trainingproject.news.news_fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.news.NewsDescriptionActivity
import com.fadineg.trainingproject.news.model.Articles
import com.fadineg.trainingproject.news.model.News
import java.util.*

@InjectViewState
class NewsFragmentPresenter : MvpPresenter<NewsFragmentView>(), ItemClickListener {

    lateinit var newsRecyclerAdapter: NewsRecyclerAdapter
    lateinit var itemClickListener: ItemClickListener

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setNewsRecyclerAdapter(newsRecyclerAdapter)
    }

    fun updateNewsList(newNewsList: List<News>) {
        newsRecyclerAdapter.updateNewsList(getArticlesList(newNewsList))
    }


    fun createAdapter(newsList: List<News>) {
        itemClickListener = this
        newsRecyclerAdapter = NewsRecyclerAdapter(getArticlesList(newsList), itemClickListener)
    }

    private fun getArticlesList(realmList: List<News>): List<Articles>? {
        val set: MutableSet<Articles> = mutableSetOf()
        for (news in realmList) {
            if (news.categorySwitch!!) news.articles?.let { set.addAll(it) }
        }
        return ArrayList(set)
    }

    override fun onItemClick(bundle: Bundle) {
        viewState.startDescriptionActivity(bundle)
    }

    fun newInstance(context: Context): Intent? {
        return Intent(context, NewsDescriptionActivity::class.java)
    }
}