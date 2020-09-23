package com.fadineg.trainingproject.news.filters_fragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.news.eventBus.NewsBus
import com.fadineg.trainingproject.news.model.News
import org.greenrobot.eventbus.EventBus

@InjectViewState
class FiltersFragmentPresenter : MvpPresenter<FiltersFragmentView>() {
    private lateinit var filtersRecyclerAdapter: FiltersRecyclerAdapter

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setRecyclerAdapter(filtersRecyclerAdapter)
    }

    fun postNewsList() {
        EventBus.getDefault().post(NewsBus(filtersRecyclerAdapter.newsList))
    }

    fun createAdapter(newsList: List<News>) {
        filtersRecyclerAdapter = FiltersRecyclerAdapter(newsList)
    }
}