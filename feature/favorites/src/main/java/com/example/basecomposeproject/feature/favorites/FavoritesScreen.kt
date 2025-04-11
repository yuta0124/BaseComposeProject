package com.example.basecomposeproject.feature.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme
import com.example.model.Pokemon
import com.example.model.fakes
import com.example.ui.component.molecules.CenterCircleIndicator
import com.example.ui.component.organisms.PokemonItem
import kotlinx.collections.immutable.PersistentList

// TODO: ptr実装
@Composable
fun FavoritesScreen(
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        viewModel.onAction(FavoritesIntent.GetFavoritePokemons)
    }

    FavoritesScreen(
        modifier = modifier.fillMaxSize(),
        pokemons = uiState.pokemons,
        isLoading = uiState.isLoading,
        onAction = viewModel::onAction,
        navigateToDetail = navigateToDetail,
    )
}

@Composable
fun FavoritesScreen(
    pokemons: PersistentList<Pokemon>,
    isLoading: Boolean,
    onAction: (FavoritesIntent) -> Unit,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
) = Scaffold(modifier = modifier) { innerPadding ->
    if (isLoading) {
        CenterCircleIndicator(
            modifier = Modifier.padding(
                innerPadding
            )
        )
    }

    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
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
                onFavoriteClick = { pokemon ->
                    onAction(FavoritesIntent.DeleteFavoritePokemon(pokemon))
                },
                onClick = navigateToDetail,
            )
        }
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() = BaseComposeProjectTheme {
    FavoritesScreen(
        pokemons = Pokemon.fakes(),
        isLoading = false,
        onAction = {},
        navigateToDetail = {},
    )
}
