package com.app.news.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object OkHttpModule {

    @Provides
    @Singleton
    fun providersOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Accept", "application/json")
                    .addHeader("Authorization", "92114aa7e66b4ea0a646573dacff7fda").build()
                chain.proceed(request)
            })
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
    }
}