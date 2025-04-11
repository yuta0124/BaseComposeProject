package com.example.basecomposeproject.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.basecomposeproject.feature.detail.detailScreen
import com.example.basecomposeproject.feature.detail.navigateToDetail
import com.example.basecomposeproject.feature.favorites.favoritesScreen
import kotlinx.serialization.Serializable

@Serializable
data object FavoritesGraph

internal fun NavGraphBuilder.favoritesGraph(
    navController: NavController,
    startDestination: Any,
) = navigation<FavoritesGraph>(
    startDestination = startDestination,
) {
    favoritesScreen(navigateToDetail = navController::navigateToDetail)
    detailScreen()
}
