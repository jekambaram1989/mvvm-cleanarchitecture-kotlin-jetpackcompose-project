package com.app.news.feature.worldnews.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.app.news.core.network.model.Article
import com.app.news.feature.worldnews.WorldNewsScreen
import kotlinx.serialization.Serializable

@Serializable
object WorldNewsRoute

fun NavController.navigateToWorldNews() {
    navigate(WorldNewsRoute)
}

fun NavGraphBuilder.worldNewsScreen(
    paddingValues: PaddingValues,
    onItemClick: (Article) -> Unit,
) {
    composable<WorldNewsRoute> {
        WorldNewsScreen(paddingValues, onItemClick = onItemClick)
    }
}