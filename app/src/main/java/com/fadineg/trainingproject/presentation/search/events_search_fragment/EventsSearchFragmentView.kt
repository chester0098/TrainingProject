package com.fadineg.trainingproject.presentation.search.events_search_fragment

import com.arellomobile.mvp.MvpView

interface EventsSearchFragmentView : MvpView {
    fun updateAdapter(searchString: String)
    fun setPlugVisible()
    fun setPlugGone()
}