package com.app.news.core.network.model

data class WorldNews(
    val status: String,
    val totalResults: Long,
    val articles: MutableList<Article>,
    var category: String
)