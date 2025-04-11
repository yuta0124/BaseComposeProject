package com.example.basecomposeproject.feature.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object Search

fun NavGraphBuilder.searchScreen(
    navigateToDetail: (String) -> Unit,
) = composable<Search> {
    SearchScreen(
        navigateToDetail = navigateToDetail,
    )
}
