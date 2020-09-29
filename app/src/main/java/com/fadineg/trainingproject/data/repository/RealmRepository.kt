package com.fadineg.trainingproject.data.repository

import com.fadineg.trainingproject.domain.model.news.News
import com.fadineg.trainingproject.domain.repositoryInterface.RealmRepositoryInterface
import io.realm.Realm
import javax.inject.Inject

class RealmRepository @Inject constructor() : RealmRepositoryInterface {
    lateinit var realm: Realm
    override fun setNewsInRealm(news: List<News>) {
        realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync { realm ->
            realm.deleteAll()
            realm.copyToRealmOrUpdate(news)
        }
        realm.close()
    }

    override fun getNewsFromRealm(): MutableList<News> {
        realm = Realm.getDefaultInstance()
        val realmResults = realm.where(News::class.java)?.findAllAsync()
        return realm.copyFromRealm(realmResults)
    }
}