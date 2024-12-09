package com.example.basecomposeproject.feature.search

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.platform.LocalContext
import com.example.basecomposeproject.core.common.compose.EventEffect
import com.example.basecomposeproject.core.common.compose.EventFlow
import com.example.data.network.repository.IPokemonRepository
import com.example.model.Pokemon
import dagger.hilt.android.EntryPointAccessors
import io.github.takahirom.rin.rememberRetained
import kotlinx.collections.immutable.toPersistentList

private const val LOAD_LIMIT = 20

/**
 * PresenterのInjectは以下でできそう、@EntryPointでいけそう
 * 複数のRepositoryに依存する時、ボイラープレートが増えそうなのが懸念点
 * https://medium.com/androiddevelopers/dependency-injection-in-compose-a2db897e6f11#:~:text=%E3%81%8C%E3%81%82%E3%82%8A%E3%81%BE%E3%81%99%E3%80%82-,%E3%82%A8%E3%83%B3%E3%83%88%E3%83%AA%E3%83%BC%E3%83%9D%E3%82%A4%E3%83%B3%E3%83%88%E3%82%92%E4%BD%BF%E7%94%A8%E3%81%99%E3%82%8B,-Hilt%20%E3%81%AF%E3%80%81%20%E3%82%92
 */
sealed interface SearchScreenEvent {
    data object GetPokemons : SearchScreenEvent
}

@Composable
fun searchPresenter(
    events: EventFlow<SearchScreenEvent>,
): SearchScreenUiState {
    var isLoading by rememberRetained { mutableStateOf(false) }
    var pokemons = rememberRetained { mutableStateListOf<Pokemon>() }
    var offset by rememberRetained { mutableIntStateOf(0) }

    val activity = LocalContext.current as Activity
    val pokemonRepo = remember {
        EntryPointAccessors.fromActivity(
            activity,
            IPokemonRepository::class.java,
        )
    }

    EventEffect(events) { event ->
        when (event) {
            SearchScreenEvent.GetPokemons -> {
                isLoading = true
                pokemonRepo.getPokemons(limit = LOAD_LIMIT, offset = offset).fold(
                    ifLeft = { _ ->
                        isLoading = false
                        // TODO: エラーハンドリング
                    },
                    ifRight = { response ->
                        isLoading = false
                        pokemons = response.pokemons.toMutableStateList()
                    }
                )
            }
        }
    }

    return when {
        isLoading -> SearchScreenUiState.Loading
        pokemons.isNotEmpty() -> SearchScreenUiState.Loaded(pokemons.toPersistentList())
        else -> SearchScreenUiState.Empty
    }
}
