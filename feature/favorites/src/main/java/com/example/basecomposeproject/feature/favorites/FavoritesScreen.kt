package com.example.basecomposeproject.feature.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme
import com.example.ui.component.molecules.CenterCircleIndicator
import kotlinx.serialization.Serializable

@Serializable
data object Favorites

fun NavGraphBuilder.favoritesScreen() = composable<Favorites> {
    FavoritesScreen()
}

sealed interface FavoritesIntent {
    data class SwitchFavorite(val name: String) : FavoritesIntent
}

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FavoritesScreen(
        isLoading = uiState.isLoading,
        modifier = modifier.fillMaxSize(),
    )
}

@Composable
fun FavoritesScreen(
    isLoading: Boolean,
    modifier: Modifier = Modifier
) = Scaffold(modifier = modifier) { innerPadding ->
    if (isLoading) {
        CenterCircleIndicator(
            modifier = Modifier.padding(
                innerPadding
            )
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("お気に入り一覧画面")
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() = BaseComposeProjectTheme {
    FavoritesScreen(
        isLoading = false,
    )
}
