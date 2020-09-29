package com.fadineg.trainingproject.di

import com.fadineg.trainingproject.domain.interactor.NewsInteractor
import com.fadineg.trainingproject.presentation.main.MainPresenter
import com.fadineg.trainingproject.presentation.news.news_fragment.NewsFragmentPresenter
import com.fadineg.trainingproject.presentation.search.events_search_fragment.EventsSearchFragmentPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, InteractorModule::class])
interface AppComponent {
    fun inject(mainPresenter: MainPresenter)
    fun inject(newsFragmentPresenter: NewsFragmentPresenter)
    fun inject(eventsSearchFragmentPresenter: EventsSearchFragmentPresenter)
    fun inject(newsInteractor: NewsInteractor)

}