package com.app.news.core.data.repository

import com.app.news.core.network.model.WorldNews

interface WorldNewsRepository {
    suspend fun getWorldNews(category: String): WorldNews
}