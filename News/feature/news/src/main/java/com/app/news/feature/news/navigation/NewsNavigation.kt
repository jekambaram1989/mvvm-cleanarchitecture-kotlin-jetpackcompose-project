package com.app.news.feature.news.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.news.core.network.model.Article
import com.app.news.feature.news.NewsScreen
import kotlinx.serialization.Serializable


@Serializable
object NewsRoute

fun NavController.navigateToNews() {
    navigate(route = NewsRoute)
}

fun NavGraphBuilder.newsScreen(paddingValues: PaddingValues, onItemClick: (Article) -> Unit) {
    composable<NewsRoute>() {
        NewsScreen(
            paddingValues,
            onItemClick = onItemClick
        )
    }
}