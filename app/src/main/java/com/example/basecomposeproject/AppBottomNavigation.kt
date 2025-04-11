package com.example.basecomposeproject

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.basecomposeproject.feature.favorites.Favorites
import com.example.basecomposeproject.feature.search.Search
import com.example.basecomposeproject.navigation.FavoritesGraph
import com.example.basecomposeproject.navigation.SearchGraph
import kotlinx.serialization.Serializable

@Composable
fun AppBottomNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val bottomNavItems = listOf(
        BottomNavItems.SearchRoute,
        BottomNavItems.FavoritesRoute,
    )
    val currentDestination = remember {
        derivedStateOf {
            navBackStackEntry?.destination
        }
    }
    val bottomBarVisible = remember {
        derivedStateOf {
            when (currentDestination.value?.route) {
                Search::class.qualifiedName,
                Favorites::class.qualifiedName -> true

                else -> false
            }
        }
    }

    AnimatedVisibility(
        visible = bottomBarVisible.value,
    ) {
        BottomAppBar(modifier = modifier) {
            bottomNavItems.forEach { navItem ->
                val isSelected =
                    currentDestination.value?.hierarchy?.any { it.route == navItem.route::class.qualifiedName } == true
                val icon = when (navItem) {
                    BottomNavItems.SearchRoute -> Icons.Default.Search
                    BottomNavItems.FavoritesRoute -> Icons.Default.Favorite
                }
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                        )
                    },
                    label = { Text(navItem.title) },
                    selected = isSelected,
                    onClick = {
                        navController.navigate(navItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Serializable
sealed class BottomNavItems<T>(val title: String, val route: T) {
    data object SearchRoute : BottomNavItems<SearchGraph>("search", SearchGraph)
    data object FavoritesRoute : BottomNavItems<FavoritesGraph>("favorites", FavoritesGraph)
}
