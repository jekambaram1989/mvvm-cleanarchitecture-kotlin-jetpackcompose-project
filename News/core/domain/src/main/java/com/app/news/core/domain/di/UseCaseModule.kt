package com.app.news.core.domain.di

import com.app.news.core.data.repository.NewsRepository
import com.app.news.core.data.repository.WorldNewsRepository
import com.app.news.core.domain.NewsUseCase
import com.app.news.core.domain.WorldNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesNewsUseCase(repository: NewsRepository): NewsUseCase {
        return NewsUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesWorldUseCase(repository: WorldNewsRepository): WorldNewsUseCase {
        return WorldNewsUseCase(repository)
    }
}