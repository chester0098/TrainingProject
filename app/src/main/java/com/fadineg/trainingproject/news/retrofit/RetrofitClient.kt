package com.fadineg.trainingproject.news.retrofit

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.fadineg.trainingproject.news.JsonInArray
import com.fadineg.trainingproject.news.eventBus.NewsBus
import com.fadineg.trainingproject.news.model.News
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.RealmList
import org.greenrobot.eventbus.EventBus
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient() {
    private var baseUrl = "https://practicaltasks-55335.firebaseio.com/"

    private val getInstance: ApiService by lazy {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        retrofit.create(ApiService::class.java)
    }

    fun downloadData(context: Context) {
        getInstance.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response -> onResponse(response) }, { t -> onFailure(t, context) })
    }

    @SuppressLint("WrongConstant")
    private fun onFailure(t: Throwable, context: Context) {
        Toast.makeText(context, "Ошибка сети", 1000).show()

        val jsonInArray = JsonInArray()

        EventBus.getDefault().post(NewsBus(jsonInArray.newsPars(context)))
    }

    private fun onResponse(response: RealmList<News>) {

        EventBus.getDefault().post(NewsBus(response))

    }
}

