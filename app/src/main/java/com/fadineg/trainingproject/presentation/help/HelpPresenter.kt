package com.fadineg.trainingproject.presentation.help

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.domain.CreateHelpList
import com.fadineg.trainingproject.domain.model.Help

@InjectViewState
class HelpPresenter : MvpPresenter<HelpView>() {
    private lateinit var createHelpList: CreateHelpList
    fun getHelpList(): MutableList<Help> {
        createHelpList = CreateHelpList()
        return createHelpList.getHelpList()
    }
}