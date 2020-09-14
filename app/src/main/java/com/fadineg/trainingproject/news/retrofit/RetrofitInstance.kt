package com.fadineg.trainingproject.news.retrofit

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private var baseUrl = "https://practicaltasks-55335.firebaseio.com/"
        val getInstance: ApiService by lazy {
            val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            retrofit.create(ApiService::class.java)
        }
    }
}