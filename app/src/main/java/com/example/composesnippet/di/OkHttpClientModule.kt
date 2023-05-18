package com.example.composesnippet.di

import com.example.composesnippet.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object OkHttpClientModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor { chain ->
                val builder = chain.request().newBuilder()
                    .addHeader(Config.FOOTBALL_API_KEY_NAME, Config.FOOTBALL_API_KEY_VALUE)

                chain.proceed(builder.build())
            }
            .build()
    }
}