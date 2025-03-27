package com.example.basecomposeproject.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme
import com.example.model.Pokemon
import com.example.model.fakes
import com.example.ui.component.molecules.CenterCircleIndicator
import com.example.ui.component.organisms.PokemonItem
import kotlinx.collections.immutable.PersistentList
import kotlinx.serialization.Serializable

@Serializable
data object Search

fun NavGraphBuilder.searchScreen() = composable<Search> {
    SearchScreen()
}

sealed interface SearchIntent {
    data object Refresh : SearchIntent
    data class SwitchFavorite(val pokemon: Pokemon) : SearchIntent
}

// TODO: pull to refresh
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        modifier = modifier.fillMaxSize(),
        pokemons = uiState.pokemons,
        isLoading = uiState.isLoading,
        onAction = viewModel::onAction,
    )
}

@Composable
fun SearchScreen(
    pokemons: PersistentList<Pokemon>,
    isLoading: Boolean,
    onAction: (SearchIntent) -> Unit,
    modifier: Modifier = Modifier,
) = Scaffold(
    modifier = modifier,
    contentWindowInsets = WindowInsets(0.dp),
) { innerPadding ->
    if (isLoading) {
        CenterCircleIndicator(
            modifier = Modifier.padding(
                innerPadding
            )
        )
    }
    LazyVerticalGrid(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(innerPadding),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(pokemons) {
            PokemonItem(
                it,
                modifier = Modifier
                    .aspectRatio(1f),
                onFavoriteClick = { name ->
                    onAction(SearchIntent.SwitchFavorite(name))
                },
            )
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() = BaseComposeProjectTheme {
    SearchScreen(
        pokemons = Pokemon.fakes(),
        isLoading = false,
        onAction = {},
        modifier = Modifier.fillMaxSize(),
    )
}
