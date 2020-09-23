package com.fadineg.trainingproject.main

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.R
import com.fadineg.trainingproject.RealmManager
import com.fadineg.trainingproject.help.HelpFragment
import com.fadineg.trainingproject.news.eventBus.NewsBus
import com.fadineg.trainingproject.news.filters_fragment.FiltersFragment
import com.fadineg.trainingproject.news.news_fragment.NewsFragment
import com.fadineg.trainingproject.news.retrofit.RetrofitClient
import com.fadineg.trainingproject.profile.ProfileFragment
import com.fadineg.trainingproject.search.search_fragment.SearchFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.io.Serializable

@InjectViewState
class MainPresenter() : MvpPresenter<MainView>() {
    companion object {
        const val REQUEST_TAKE_PHOTO = 1
        const val REQUEST_CHOOSE_PHOTO = 2
        const val FILES_DIR = "Pictures"
        const val FILE_NAME = "temp.jpg"
        const val NEWS_BUNDLE_KEY = "newsList"
        const val PROFILE_FRAGMENT_TAG = "profile"
        const val NEWS_FRAGMENT_TAG = "news"
        const val SEARCH_FRAGMENT_TAG = "search"
        const val HELP_FRAGMENT_TAG = "help"
        const val FILTERS_FRAGMENT_TAG = "filters"
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

    fun onNavigationItemSelected(itemId: Int) {
        when (itemId) {
            R.id.bnv_profile -> viewState.replaceFragment(ProfileFragment(), PROFILE_FRAGMENT_TAG)

            R.id.bnv_history -> {
            }

            R.id.bnv_help -> viewState.replaceFragment(HelpFragment(), HELP_FRAGMENT_TAG)

            R.id.bnv_search -> viewState.replaceFragment(SearchFragment(), SEARCH_FRAGMENT_TAG)

            R.id.bnv_news -> {
                val newsFragment = NewsFragment()
                val bundle = Bundle()
                bundle.putSerializable(NEWS_BUNDLE_KEY, realmManager.getNewsFromRealm() as Serializable)
                newsFragment.arguments = bundle
                viewState.replaceFragment(newsFragment, NEWS_FRAGMENT_TAG)
            }
        }
    }

    fun downloadData(context: Context) {
        retrofitClient = RetrofitClient()
        retrofitClient.downloadData(context)
    }

    fun openFilters() {
        val filtersFragment = FiltersFragment()
        val bundle = Bundle()
        bundle.putSerializable(NEWS_BUNDLE_KEY, realmManager.getNewsFromRealm() as Serializable)
        filtersFragment.arguments = bundle
        viewState.addFragment(filtersFragment, FILTERS_FRAGMENT_TAG)
    }

    fun onActivityResult(requestCode: Int, data: Intent, context: Context) {
        if (requestCode == REQUEST_TAKE_PHOTO) {
            val path: String = context.getExternalFilesDir(FILES_DIR).toString() + File.separator + FILE_NAME
            val takenImage = BitmapFactory.decodeFile(path)
            viewState.changeUserPhoto(takenImage)
        } else if (requestCode == REQUEST_CHOOSE_PHOTO) {
            try {
                val imageUri = data.data
                val imageStream: InputStream? = imageUri?.let { context.getContentResolver().openInputStream(it) }
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                viewState.changeUserPhoto(selectedImage)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        realmManager.closeRealm()
        EventBus.getDefault().unregister(this)
    }
}