package com.example.basecomposeproject.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basecomposeproject.core.design.component.organisms.PokemonItem
import com.example.basecomposeproject.core.design.theme.ui.BaseComposeProjectTheme
import com.example.model.Pokemon
import com.example.model.fakes
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.android.components.ActivityComponent

@CircuitInject(SearchScreen::class, ActivityComponent::class)
@Composable
fun SearchScreenUi(
    state: UiState,
    modifier: Modifier = Modifier,
) = Scaffold { innerPadding ->
    LaunchedEffect(Unit) {
        state.eventSink(Event.FetchList)
    }
    Column(
        modifier = modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(12.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.pokemons) { pokemon ->
                PokemonItem(
                    pokemon,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun SearchScreenUiPreview() = BaseComposeProjectTheme {
    SearchScreenUi(
        state = UiState(
            pokemons = Pokemon.fakes(),
            eventSink = {},
        )
    )
}
