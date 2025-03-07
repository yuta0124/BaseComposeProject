package com.example.basecomposeproject.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
    data class SwitchFavorite(val name: String) : SearchIntent
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
) = Scaffold(contentWindowInsets = WindowInsets(0.dp)) { innerPadding ->
    if (isLoading) {
        CenterCircleIndicator(
            modifier = Modifier.padding(
                innerPadding
            )
        )
    }
    Column(
        modifier = modifier
            .padding(innerPadding)
            .fillMaxSize(),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(pokemons) { pokemon ->
                PokemonItem(
                    pokemon,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    onFavoriteClick = { name ->
                        onAction(SearchIntent.SwitchFavorite(name))
                    },
                )
            }
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

@Preview
@Composable
fun ScreenshotTestSample() = BaseComposeProjectTheme {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("screenshot sample")
            Text("screenshot sample")
        }
    }
}
