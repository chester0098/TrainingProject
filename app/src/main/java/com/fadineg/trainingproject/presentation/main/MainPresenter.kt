package com.fadineg.trainingproject.presentation.main

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.TrainingApplication
import com.fadineg.trainingproject.domain.interactor.NewsInteractor
import com.fadineg.trainingproject.domain.model.eventBus.NewsBus
import com.fadineg.trainingproject.domain.model.news.News
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import javax.inject.Inject

@InjectViewState
class MainPresenter() : MvpPresenter<MainView>() {
    companion object {
        const val FILES_DIR = "Pictures"
        const val FILE_NAME = "temp.jpg"
    }

    init {
        TrainingApplication.appComponent.inject(this)
    }

    @Inject
    lateinit var newsInteractor: NewsInteractor

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(newsBus: NewsBus) {
        newsInteractor.setNewsToDB(newsBus.newsList)
        viewState.updateNewsFragment(newsBus.newsList)
    }

    fun downloadData(context: Context) {
        newsInteractor.startDownload(context)
    }

    fun getNews(): List<News>? {
        return newsInteractor.getNewsFromDB()
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
        EventBus.getDefault().unregister(this)
    }
}