package com.example.basecomposeproject.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.basecomposeproject.AppBottomNavigation
import com.example.basecomposeproject.feature.favorites.Favorites
import com.example.basecomposeproject.feature.search.Search

@Composable
fun BaseComposeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { AppBottomNavigation(navController) },
    ) { padding ->
        NavHost(
            modifier = modifier.padding(padding),
            navController = navController,
            startDestination = SearchGraph,
        ) {
            searchGraph(
                navController = navController,
                startDestination = Search,
            )

            favoritesGraph(
                navController = navController,
                startDestination = Favorites,
            )
        }
    }
}
