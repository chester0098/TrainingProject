package com.fadineg.trainingproject.help

import com.arellomobile.mvp.MvpView

interface HelpView : MvpView {
    fun setRecyclerAdapter(helpRecyclerAdapter: HelpRecyclerAdapter?)
}