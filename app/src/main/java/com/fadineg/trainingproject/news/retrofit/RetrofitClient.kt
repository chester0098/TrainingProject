package com.fadineg.trainingproject.news.retrofit

import android.content.Context
import android.widget.Toast
import com.fadineg.trainingproject.news.JsonInArray
import com.fadineg.trainingproject.news.eventBus.NewsBus
import com.fadineg.trainingproject.news.model.News
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.RealmList
import org.greenrobot.eventbus.EventBus

class RetrofitClient() {

    fun downloadData(context: Context) {
        RetrofitInstance.getInstance.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response -> onResponse(response, context) }, { t -> onFailure(t, context) })
    }

    private fun onFailure(t: Throwable, context: Context) {
        Toast.makeText(context, "Ошибка сети", Toast.LENGTH_SHORT).show()

        val jsonInArray = JsonInArray()

        EventBus.getDefault().post(NewsBus(jsonInArray.newsPars(context)))
    }

    private fun onResponse(response: RealmList<News>, context: Context) {
        Toast.makeText(context, "Данные загружены", Toast.LENGTH_SHORT).show()

        EventBus.getDefault().post(NewsBus(response))

    }
}

