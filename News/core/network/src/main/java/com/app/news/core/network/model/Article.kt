package com.app.news.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val source: Source? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    val url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null
)
