package com.fadineg.trainingproject.search.events_search_fragment

import com.arellomobile.mvp.MvpView

interface EventsSearchFragmentView : MvpView {
    fun setSearchRecyclerView(eventsRecyclerAdapter: EventsRecyclerAdapter)
    fun setPlugVisible()
    fun setPlugGone()
}