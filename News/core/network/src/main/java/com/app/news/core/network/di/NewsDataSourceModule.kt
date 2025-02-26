package com.app.news.core.network.di

import com.app.news.core.network.NewsNetworkDataSource
import com.app.news.core.network.retrofit.RetrofitNewsNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface NewsDataSourceModule {

    @Binds
    fun binds(impl: RetrofitNewsNetwork): NewsNetworkDataSource
}