package com.example.basecomposeproject.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.basecomposeproject.feature.detail.detailScreen
import com.example.basecomposeproject.feature.detail.navigateToDetail
import com.example.basecomposeproject.feature.search.searchScreen
import kotlinx.serialization.Serializable

@Serializable
data object SearchGraph

internal fun NavGraphBuilder.searchGraph(
    navController: NavController,
    startDestination: Any,
) = navigation<SearchGraph>(
    startDestination = startDestination,
) {
    searchScreen(navigateToDetail = navController::navigateToDetail)
    detailScreen()
}
