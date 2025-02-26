package com.app.news.core.common.result

sealed class NewsResult<out T> {
    data class Success<T>(val data: T) : NewsResult<T>()
    data class Error(val exception: Throwable) : NewsResult<Nothing>()
    data object Loading : NewsResult<Nothing>()
}