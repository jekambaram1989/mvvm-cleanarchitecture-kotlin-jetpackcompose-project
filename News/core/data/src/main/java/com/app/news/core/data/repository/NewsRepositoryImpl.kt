package com.app.news.core.data.repository

import com.app.news.core.network.NewsNetworkDataSource
import com.app.news.core.network.model.News
import javax.inject.Inject

internal class NewsRepositoryImpl @Inject constructor(
    private val network: NewsNetworkDataSource
) : NewsRepository {
    override suspend fun getNews(country: String): News {
        return network.getNews(country)
    }
}