package com.app.news.core.network

import com.app.news.core.network.model.News
import com.app.news.core.network.model.WorldNews

interface NewsNetworkDataSource {

    suspend fun getNews(country: String): News

    suspend fun getWorldNews(category: String): WorldNews
}