package com.app.news.core.data.repository

import com.app.news.core.network.NewsNetworkDataSource
import com.app.news.core.network.model.WorldNews
import javax.inject.Inject

internal class WorldNewsRepositoryImpl @Inject constructor(private val network: NewsNetworkDataSource) :
    WorldNewsRepository {

    override suspend fun getWorldNews(category: String): WorldNews {
        return network.getWorldNews(category)
    }
}