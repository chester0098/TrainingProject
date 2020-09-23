package com.fadineg.trainingproject.search.search_fragment

import androidx.appcompat.widget.SearchView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fadineg.trainingproject.search.RxSearchView
import com.fadineg.trainingproject.search.SearchStringBus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.TimeUnit

@InjectViewState
class SearchFragmentPresenter : MvpPresenter<SearchFragmentView>() {
    var searchViewString: String? = ""

    fun searchViewSubscribe(searchView: SearchView) {
        val searchViewObservable = RxSearchView.searchViewObservable(searchView)
        searchViewObservable.subscribeOn(Schedulers.io())
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<String?>() {
                    override fun onNext(s: @NonNull String?) {
                        searchViewString = s
                        EventBus.getDefault().post(SearchStringBus(s))
                    }

                    override fun onError(e: @NonNull Throwable?) {}
                    override fun onComplete() {}
                })
    }

    fun eventBusPost() {
        EventBus.getDefault().post(SearchStringBus(searchViewString))
    }
}