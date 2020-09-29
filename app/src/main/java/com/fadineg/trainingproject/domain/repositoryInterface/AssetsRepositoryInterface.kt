package com.fadineg.trainingproject.domain.repositoryInterface

import android.content.Context
import com.fadineg.trainingproject.domain.model.news.News

interface AssetsRepositoryInterface {
    fun getNewsFromAssets(context: Context?): List<News>
}