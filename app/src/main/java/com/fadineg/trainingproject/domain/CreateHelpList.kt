package com.fadineg.trainingproject.domain

import com.fadineg.trainingproject.R
import com.fadineg.trainingproject.domain.model.Help

class CreateHelpList {
    fun getHelpList(): MutableList<Help> {
        val helpList: MutableList<Help> = mutableListOf()
        helpList.add(Help(R.string.helpList_category_children, R.drawable.children))
        helpList.add(Help(R.string.helpList_category_adults, R.drawable.adults))
        helpList.add(Help(R.string.helpList_category_elderly, R.drawable.elderly))
        helpList.add(Help(R.string.helpList_category_animals, R.drawable.animals))
        helpList.add(Help(R.string.helpList_category_event, R.drawable.event))
        return helpList
    }
}