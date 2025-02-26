package com.app.news.presentation.bottomnavigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.app.news.presentation.BottomNavItems.Companion.navItems
import com.app.news.feature.news.navigation.NewsRoute
import com.app.news.feature.news.navigation.navigateToNews
import com.app.news.feature.worldnews.navigation.WorldNewsRoute
import com.app.news.feature.worldnews.navigation.navigateToWorldNews
import kotlin.reflect.KClass

@Composable
internal fun BottomNav(navController: NavController) {
    val previousDestination = remember {
        mutableStateOf<NavDestination?>(null)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination.also { destination ->
        if (destination != null) {
            previousDestination.value = destination
        }
    } ?: previousDestination.value

    BottomNavigation(
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        navItems.forEach { items ->
            val selected = currentDestination
                .isRouteInHierarchy(items.route)

            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = Color.Black,
                    selectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                    unselectedIconColor = Color.White,
                    indicatorColor = Color.Black
                ),
                label = {
                    Text(text = stringResource(id = items.title))
                },
                selected = selected,
                onClick = {
                    val topLevelNavOptions = navOptions {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    when (items.route) {
                        NewsRoute::class -> navController.navigateToNews()
                        WorldNewsRoute::class -> navController.navigateToWorldNews()
                    }
                }, icon = {
                    Icon(
                        imageVector = items.icon,
                        contentDescription = stringResource(id = items.title)
                    )
                })
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false