package com.example.basecomposeproject.feature.favorites

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object Favorites

fun NavGraphBuilder.favoritesScreen(
    navigateToDetail: (String) -> Unit,
) = composable<Favorites> {
    FavoritesScreen(
        navigateToDetail = navigateToDetail,
    )
}
