package com.example.basecomposeproject

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme
import com.example.basecomposeproject.feature.favorites.Favorites
import com.example.basecomposeproject.feature.favorites.favoritesScreen
import com.example.basecomposeproject.feature.search.Search
import com.example.basecomposeproject.feature.search.searchScreen
import kotlinx.serialization.Serializable

@Composable
fun BaseComposeApp(modifier: Modifier = Modifier) = BaseComposeProjectTheme {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        BaseComposeNavHost()
    }
}

// TODO: この中にBottomnavigation入れる、また、現在の画面の型を見て表示非表示を切り替える
@Composable
fun BaseComposeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) = Scaffold(
    bottomBar = { AppBottomNavigation(navController) },
) { padding ->
    NavHost(
        modifier = modifier.padding(padding),
        navController = navController,
        startDestination = SearchGraph,
    ) {
        searchGraph(Search)
        favoritesGraph(Favorites)
    }
}

@Serializable
sealed class BottomNavItems<T>(val title: String, val route: T) {
    data object SearchRoute : BottomNavItems<SearchGraph>("search", SearchGraph)
    data object FavoritesRoute : BottomNavItems<FavoritesGraph>("favorites", FavoritesGraph)
}

@Composable
fun AppBottomNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavItems = listOf(
        BottomNavItems.SearchRoute,
        BottomNavItems.FavoritesRoute,
    )

    BottomAppBar(modifier = modifier) {
        bottomNavItems.forEach { navItem ->
            val isSelected =
                currentDestination?.hierarchy?.any { it.route == navItem.route::class.qualifiedName } == true
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

@Serializable
data object SearchGraph

fun NavGraphBuilder.searchGraph(
    startDestination: Any,
) = navigation<SearchGraph>(
    startDestination = startDestination,
) {
    searchScreen()
}

@Serializable
data object FavoritesGraph

fun NavGraphBuilder.favoritesGraph(
    startDestination: Any,
) = navigation<FavoritesGraph>(
    startDestination = startDestination,
) {
    favoritesScreen()
}
