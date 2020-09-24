package com.fadineg.trainingproject.main

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.RealmManager
import com.fadineg.trainingproject.news.eventBus.NewsBus
import com.fadineg.trainingproject.news.model.News
import com.fadineg.trainingproject.news.retrofit.RetrofitClient
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

@InjectViewState
class MainPresenter() : MvpPresenter<MainView>() {
    companion object {
        const val FILES_DIR = "Pictures"
        const val FILE_NAME = "temp.jpg"
        var realmManager: RealmManager = RealmManager()
    }

    private lateinit var retrofitClient: RetrofitClient

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        realmManager.createInstance()
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(newsBus: NewsBus) {
        realmManager.setNewsInRealm(newsBus.newsList)
        viewState.updateNewsFragment(newsBus.newsList)
    }

    fun downloadData(context: Context) {
        retrofitClient = RetrofitClient()
        retrofitClient.downloadData(context)
    }

    fun getNews(): List<News>? {
        return realmManager.getNewsFromRealm()
    }

    fun takePhoto(data: Intent, context: Context) {
        val path: String = context.getExternalFilesDir(FILES_DIR).toString() + File.separator + FILE_NAME
        val takenImage = BitmapFactory.decodeFile(path)
        viewState.updateUserPhoto(takenImage)
    }

    fun choosePhoto(data: Intent, context: Context) {
        try {
            val imageUri = data.data
            val imageStream: InputStream? = imageUri?.let { context.contentResolver.openInputStream(it) }
            val selectedImage = BitmapFactory.decodeStream(imageStream)
            viewState.updateUserPhoto(selectedImage)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        realmManager.closeRealm()
        EventBus.getDefault().unregister(this)
    }
}