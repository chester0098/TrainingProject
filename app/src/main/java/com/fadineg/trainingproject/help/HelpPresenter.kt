package com.fadineg.trainingproject.help

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

@InjectViewState
class HelpPresenter : MvpPresenter<HelpView>() {
    private lateinit var createHelpList: CreateHelpList
    fun getHelpList(): MutableList<Help> {
        createHelpList = CreateHelpList()
        return createHelpList.getHelpList()
    }
}