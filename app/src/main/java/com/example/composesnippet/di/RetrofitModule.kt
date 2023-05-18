package com.example.composesnippet.di

import com.example.composesnippet.Config
import com.example.composesnippet.network.api.FootballApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

//    val gson = GsonBuilder()
//        .registerTypeAdapter()
//        .create()

    @Singleton
    @Provides
    fun provideFootballApi(okHttpClient: OkHttpClient): FootballApi {
        return Retrofit.Builder()
            .baseUrl(Config.FOOTBALL_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FootballApi::class.java)
    }
}