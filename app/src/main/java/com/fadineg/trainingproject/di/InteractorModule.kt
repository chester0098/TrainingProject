package com.fadineg.trainingproject.di

import com.fadineg.trainingproject.domain.converter.ArticlesFromNews
import com.fadineg.trainingproject.domain.interactor.NewsInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {
    @Singleton
    @Provides
    fun provideNewsInteractor(): NewsInteractor {
        return NewsInteractor()
    }

    @Singleton
    @Provides
    fun provideArticlesFromNews(): ArticlesFromNews {
        return ArticlesFromNews()
    }
}