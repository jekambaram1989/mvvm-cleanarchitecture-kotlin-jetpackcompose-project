package com.app.news.feature.details.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.app.news.core.network.model.Article
import com.app.news.feature.details.DetailsScreen
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf


@Serializable
data class DetailsRoute(val article: Article)

fun NavController.navigateToDetailsScreen(article: Article) {
    navigate(route = DetailsRoute(article))
}

fun NavGraphBuilder.detailsScreen(paddingValues: PaddingValues, onClick: () -> Unit) {
    composable<DetailsRoute>(
        typeMap = mapOf(
            typeOf<Article>() to CustomNavType.ArticleType,
        )
    ) {
        val args = it.toRoute<DetailsRoute>()
        DetailsScreen(
            paddingValues,
            args.article,
            onClick
        )
    }
}