package com.app.news.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.app.news.feature.details.navigation.detailsScreen
import com.app.news.feature.details.navigation.navigateToDetailsScreen
import com.app.news.feature.news.navigation.NewsRoute
import com.app.news.feature.news.navigation.newsScreen
import com.app.news.feature.worldnews.navigation.worldNewsScreen

@Composable
fun AppNavigation(navController: NavController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = NewsRoute
    ) {
        newsScreen(innerPadding, onItemClick = { article ->
            navController.navigateToDetailsScreen(article)
        })
        worldNewsScreen(innerPadding, onItemClick = { article ->
            navController.navigateToDetailsScreen(article)
        })
        detailsScreen(innerPadding, onClick = navController::popBackStack)
    }
}