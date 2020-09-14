package com.fadineg.trainingproject

import android.content.Context
import com.fadineg.trainingproject.news.model.News
import io.realm.Realm

class RealmManager(_context: Context) {
    var context: Context = _context
    var realm: Realm? = null

    fun createInstance() {
        Realm.init(context)
        realm = Realm.getDefaultInstance()
    }

    fun closeRealm() {
        realm?.close()
    }

    fun setNewsInRealm(news: List<News>) {
        realm?.executeTransactionAsync { realm ->
            realm.deleteAll()
            realm.copyToRealmOrUpdate(news)
        }
    }

    fun getNewsFromRealm(): List<News>? {
        val realmResults = realm?.where(News::class.java)?.findAllAsync()
        return realm?.copyFromRealm(realmResults)
    }
}