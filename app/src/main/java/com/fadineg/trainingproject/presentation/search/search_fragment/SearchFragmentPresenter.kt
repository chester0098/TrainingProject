package com.fadineg.trainingproject.presentation.search.search_fragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.domain.model.eventBus.SearchStringBus
import org.greenrobot.eventbus.EventBus

@InjectViewState
class SearchFragmentPresenter : MvpPresenter<SearchFragmentView>() {
    fun eventBusPost(searchViewString: String) {
        EventBus.getDefault().post(SearchStringBus(searchViewString))
    }
}