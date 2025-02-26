package com.app.news.core.data.repository

import com.app.news.core.network.model.News

interface NewsRepository {
    suspend fun getNews(country: String): News
}