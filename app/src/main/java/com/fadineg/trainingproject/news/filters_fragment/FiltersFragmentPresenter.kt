package com.fadineg.trainingproject.news.filters_fragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.news.eventBus.NewsBus
import com.fadineg.trainingproject.news.model.News
import org.greenrobot.eventbus.EventBus

@InjectViewState
class FiltersFragmentPresenter : MvpPresenter<FiltersFragmentView>() {
    fun postNewsList(newsList: List<News>) {
        EventBus.getDefault().post(NewsBus(newsList))
    }
}