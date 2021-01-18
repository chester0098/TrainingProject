package com.fadineg.trainingproject.domain.interactor

import android.content.Context
import com.fadineg.trainingproject.TrainingApplication
import com.fadineg.trainingproject.domain.model.news.News
import com.fadineg.trainingproject.domain.repositoryInterface.NetworkRepositoryInterface
import com.fadineg.trainingproject.domain.repositoryInterface.RealmRepositoryInterface
import javax.inject.Inject

class NewsInteractor {
    init {
        TrainingApplication.appComponent.inject(this)
    }

    @Inject
    lateinit var realmRepositoryInterface: RealmRepositoryInterface

    @Inject
    lateinit var networkRepositoryInterface: NetworkRepositoryInterface

    fun getNewsFromDB(): List<News>? {
        return realmRepositoryInterface.getNewsFromRealm()
    }

    fun setNewsToDB(newsList: List<News>) {
        realmRepositoryInterface.setNewsInRealm(newsList)
    }

    fun startDownload(context: Context) {
        networkRepositoryInterface.downloadNewsFromServer(context)
    }
}
