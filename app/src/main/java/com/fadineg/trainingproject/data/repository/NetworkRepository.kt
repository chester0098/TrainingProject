package com.fadineg.trainingproject.data.repository

import android.content.Context
import android.widget.Toast
import com.fadineg.trainingproject.data.api.RetrofitInstance
import com.fadineg.trainingproject.domain.model.eventBus.NewsBus
import com.fadineg.trainingproject.domain.model.news.News
import com.fadineg.trainingproject.domain.repositoryInterface.NetworkRepositoryInterface
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.RealmList
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class NetworkRepository @Inject constructor() : NetworkRepositoryInterface {
    override fun downloadNewsFromServer(context: Context) {
        RetrofitInstance.getInstance.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> onResponse(response, context) }, { t -> onFailure(t, context) })

    }

    private fun onFailure(t: Throwable, context: Context) {
        Toast.makeText(context, "Ошибка сети", Toast.LENGTH_SHORT).show()

        val assetsRepository = AssetsRepository()

        EventBus.getDefault().post(NewsBus(assetsRepository.getNewsFromAssets(context)))
    }

    private fun onResponse(response: RealmList<News>, context: Context) {
        Toast.makeText(context, "Данные загружены", Toast.LENGTH_SHORT).show()

        EventBus.getDefault().post(NewsBus(response))

    }
}

