package com.example.basecomposeproject.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.basecomposeproject.core.common.StrRes
import com.example.basecomposeproject.core.common.compose.EventFlow
import com.example.basecomposeproject.core.common.compose.rememberEventFlow
import com.example.basecomposeproject.core.design.component.molecules.AppCircularProgressIndicator
import com.example.basecomposeproject.core.design.component.organisms.PokemonItem
import com.example.model.Pokemon
import kotlinx.collections.immutable.PersistentList

// TODO: navigation component
// TODO: 画面遷移とかは直接呼び出してる
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    eventFlow: EventFlow<SearchScreenEvent> = rememberEventFlow(),
    uiState: SearchScreenUiState = searchPresenter(eventFlow),
) {
    LaunchedEffect(Unit) {
        eventFlow.tryEmit(SearchScreenEvent.GetPokemons)
    }

    SearchScreen(
        modifier = modifier,
        uiState = uiState,
    )
}

@Composable
fun SearchScreen(
    uiState: SearchScreenUiState,
    modifier: Modifier = Modifier,
) = Scaffold(
    modifier = modifier,
) { innerPadding ->
    Column(
        modifier = modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(12.dp)
    ) {
        when (uiState) {
            SearchScreenUiState.Empty -> {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(StrRes.list_empty),
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            is SearchScreenUiState.Loaded -> LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.pokemons) { pokemon ->
                    PokemonItem(
                        pokemon,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )
                }
            }

            SearchScreenUiState.Loading -> AppCircularProgressIndicator()
        }
    }
}

sealed interface SearchScreenUiState {
    data object Empty : SearchScreenUiState
    data object Loading : SearchScreenUiState
    data class Loaded(
        val pokemons: PersistentList<Pokemon>,
    ) : SearchScreenUiState
}
