package com.app.news.core.common.result.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun String.toLocalDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    val outputFormat = SimpleDateFormat("MMM dd yyyy HH:mm", Locale.getDefault())
    val date = inputFormat.parse(this)
    return date?.let { outputFormat.format(date) } ?: ""
}