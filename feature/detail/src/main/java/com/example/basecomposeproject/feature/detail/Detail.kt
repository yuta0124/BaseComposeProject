package com.example.basecomposeproject.feature.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class Detail(
    val name: String,
)

fun NavGraphBuilder.detailScreen() = composable<Detail> {
    DetailScreen()
}

fun NavController.navigateToDetail(name: String) = navigate(route = Detail(name))
