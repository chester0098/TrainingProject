package com.fadineg.trainingproject.search.events_search_fragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.main.MainPresenter
import com.fadineg.trainingproject.news.model.Articles
import com.fadineg.trainingproject.search.SearchStringBus
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@InjectViewState
class EventsSearchFragmentPresenter : MvpPresenter<EventsSearchFragmentView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        EventBus.getDefault().register(this)
    }

    fun getArticles(): List<Articles> {
        val articlesList = mutableListOf<Articles>()
        for (news in MainPresenter.realmManager.getNewsFromRealm()!!) {
            news.articles?.let { articlesList.addAll(it) }
        }
        return articlesList
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