package com.fadineg.trainingproject.di

import com.fadineg.trainingproject.data.repository.AssetsRepository
import com.fadineg.trainingproject.data.repository.NetworkRepository
import com.fadineg.trainingproject.data.repository.RealmRepository
import com.fadineg.trainingproject.domain.repositoryInterface.AssetsRepositoryInterface
import com.fadineg.trainingproject.domain.repositoryInterface.NetworkRepositoryInterface
import com.fadineg.trainingproject.domain.repositoryInterface.RealmRepositoryInterface
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRealmRepository(): RealmRepositoryInterface {
        return RealmRepository()
    }

    @Singleton
    @Provides
    fun provideNetworkRepository(): NetworkRepositoryInterface {
        return NetworkRepository()
    }

    @Singleton
    @Provides
    fun provideAssetsRepository(): AssetsRepositoryInterface {
        return AssetsRepository()
    }
}