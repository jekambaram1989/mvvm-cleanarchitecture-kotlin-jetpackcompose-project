package com.app.news.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String? = null,
    var name: String? = null,
    val description: String? = null,
    val url: String? = null,
    val category: String? = null,
    val language: String? = null,
    val country: String? = null
)