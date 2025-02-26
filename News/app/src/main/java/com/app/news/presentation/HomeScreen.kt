package com.app.news.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.app.news.navigation.AppNavigation
import com.app.news.presentation.bottomnavigation.BottomNav

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomNav(navController = navController)
        }
    ) { innerPadding ->
        AppNavigation(navController = navController, innerPadding = innerPadding)
    }
}