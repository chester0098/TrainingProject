package com.fadineg.trainingproject.data.api

import com.fadineg.trainingproject.domain.model.news.News
import io.reactivex.rxjava3.core.Observable
import io.realm.RealmList
import retrofit2.http.GET

interface ApiService {
    @GET("data/main.json")
    fun getNews(): Observable<RealmList<News>>
}