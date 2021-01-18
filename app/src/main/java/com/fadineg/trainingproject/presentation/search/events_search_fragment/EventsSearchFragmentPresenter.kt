package com.fadineg.trainingproject.presentation.search.events_search_fragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.TrainingApplication
import com.fadineg.trainingproject.domain.converter.ArticlesFromNews
import com.fadineg.trainingproject.domain.interactor.NewsInteractor
import com.fadineg.trainingproject.domain.model.eventBus.SearchStringBus
import com.fadineg.trainingproject.domain.model.news.Articles
import com.fadineg.trainingproject.domain.model.news.News
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

@InjectViewState
class EventsSearchFragmentPresenter : MvpPresenter<EventsSearchFragmentView>() {
    init {
        TrainingApplication.appComponent.inject(this)
    }

    @Inject
    lateinit var newsInteractor: NewsInteractor

    @Inject
    lateinit var articlesFromNews: ArticlesFromNews

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        EventBus.getDefault().register(this)
    }

    fun getArticles(): List<Articles> {
        return articlesFromNews.getArticlesFromNews(newsInteractor.getNewsFromDB() as MutableList<News>)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(searchStringBus: SearchStringBus) {
        if (searchStringBus.searchString == "")
            viewState.setPlugVisible()
        else {
            viewState.setPlugGone()
            viewState.updateAdapter(searchStringBus.searchString)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}