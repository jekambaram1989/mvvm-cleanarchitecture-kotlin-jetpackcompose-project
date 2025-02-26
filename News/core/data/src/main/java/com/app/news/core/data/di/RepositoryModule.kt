package com.app.news.core.data.di

import com.app.news.core.data.repository.NewsRepository
import com.app.news.core.data.repository.NewsRepositoryImpl
import com.app.news.core.data.repository.WorldNewsRepository
import com.app.news.core.data.repository.WorldNewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    internal abstract fun providesHeadlinesRepository(newsRepository: NewsRepositoryImpl): NewsRepository

    @Binds
    internal abstract fun providesWorldNewsRepository(worldNewsRepository: WorldNewsRepositoryImpl): WorldNewsRepository
}