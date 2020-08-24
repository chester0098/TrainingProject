package com.fadineg.trainingproject.news.retrofit

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.fadineg.trainingproject.news.asyncTask.NewsParsingTask
import com.fadineg.trainingproject.news.eventBus.NewsBus
import com.fadineg.trainingproject.news.model.News
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient() {
    private var base_url = "https://practicaltasks-55335.firebaseio.com/";
    private fun getInstance(): ApiService {
        val retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        return retrofit.create(ApiService::class.java)
    }

    fun downloadData(context: Context) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
                getInstance().getNews()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response -> onResponse(response) }, { t -> onFailure(t,context) }))
    }

    @SuppressLint("WrongConstant")
    private fun onFailure(t: Throwable, context: Context) {
        Toast.makeText(context, "Ошибка сети", 1000).show()
        val newsParsingTask = NewsParsingTask(context)
        newsParsingTask.execute()
    }

    private fun onResponse(response: List<News>) {
        Log.i("net", Thread.currentThread().name)
        for (i in response.indices) {
            response[i]._category_switch = true
        }
        EventBus.getDefault().post(NewsBus(response))
    }
}

