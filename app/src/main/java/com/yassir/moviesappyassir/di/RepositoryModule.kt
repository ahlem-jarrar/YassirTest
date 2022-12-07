package com.yassir.moviesappyassir.di

import com.yassir.moviesappyassir.data.RepositoryImp
import com.yassir.moviesappyassir.data.api.ApiClient
import com.yassir.moviesappyassir.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductsRepository(service: ApiClient): Repository {
        return RepositoryImp(service)
    }
}