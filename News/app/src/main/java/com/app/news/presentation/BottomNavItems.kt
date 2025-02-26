package com.app.news.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.app.news.R
import com.app.news.feature.news.navigation.NewsRoute
import com.app.news.feature.worldnews.navigation.WorldNewsRoute
import kotlin.reflect.KClass

data class BottomNavItems(@StringRes val title: Int, val route: KClass<*>, val icon: ImageVector) {
    companion object {
        val navItems = listOf(
            BottomNavItems(R.string.news, NewsRoute::class, Icons.Rounded.Home),
            BottomNavItems(R.string.worldnews, WorldNewsRoute::class, Icons.Rounded.Place)
        )
    }
}