package com.fadineg.trainingproject.domain.repositoryInterface

import android.content.Context

interface NetworkRepositoryInterface {
    fun downloadNewsFromServer(context: Context)
}