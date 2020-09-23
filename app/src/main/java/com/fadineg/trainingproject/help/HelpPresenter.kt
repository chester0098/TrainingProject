package com.fadineg.trainingproject.help

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class HelpPresenter : MvpPresenter<HelpView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val createHelpList = CreateHelpList()
        val adapter = HelpRecyclerAdapter(createHelpList.getHelpList())
        viewState.setRecyclerAdapter(adapter)
    }
}