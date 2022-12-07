package com.yassir.moviesappyassir.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.yassir.moviesappyassir.BuildConfig
import com.yassir.moviesappyassir.data.api.ApiClient
import com.yassir.moviesappyassir.utils.StatusProvider
import com.yassir.moviesappyassir.utils.StatusProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiClient = retrofit.create(ApiClient::class.java)

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {

        val loggerInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder().apply {
            val isDebug: Boolean = BuildConfig.DEBUG
            if (isDebug) {
                addInterceptor(loggerInterceptor)
            }

        }.build()

        return okHttpClient
    }

    @Singleton
    @Provides
    fun providesRetrofit(httpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
    }


    @Singleton
    @Provides
    fun providesStatusProvider(@ApplicationContext context: Context): StatusProvider =
        StatusProviderImpl(context)
}