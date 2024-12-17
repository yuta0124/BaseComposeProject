package com.example.basecomposeproject.feature.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.basecomposeproject.core.common.compose.EventEffect
import com.example.basecomposeproject.core.common.compose.EventFlow
import com.example.data.network.repository.IPokemonRepository
import com.example.data.network.repository.localPokemonRepository
import com.example.model.Pokemon
import io.github.takahirom.rin.produceRetainedState
import io.github.takahirom.rin.rememberRetained
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.catch

private const val LOAD_LIMIT = 20

sealed interface SearchScreenEvent {
    data object GetPokemons : SearchScreenEvent
}

@Composable
fun searchPresenter(
    events: EventFlow<SearchScreenEvent>,
    pokemonRepository: IPokemonRepository = localPokemonRepository()
): SearchScreenUiState {
    var isLoading by rememberRetained { mutableStateOf(true) }
    var offset by rememberRetained { mutableIntStateOf(0) }
    // TODO: safeCollectAsRetainedStateが活用できるか？
    var pokemons = produceRetainedState<PersistentList<Pokemon>>(persistentListOf()) {
        pokemonRepository
            .getPokemons(LOAD_LIMIT, offset)
            .catch {
                // TODO: エラーハンドリング
            }
            .collect {
                isLoading = false
                offset = it.pokemons.size
                value = it.pokemons
            }
    }

    EventEffect(events) { event ->
        when (event) {
            SearchScreenEvent.GetPokemons -> {
                isLoading = true
                // TODO: 呼び出すだけでflowが更新される様にするのが良さそうか？
                pokemonRepository.getPokemons(limit = LOAD_LIMIT, offset = offset).collect {
                    // TODO: ライフサイクル大丈夫か？
                }
            }
        }
    }

    return when {
        isLoading -> SearchScreenUiState.Loading
        pokemons.value.isNotEmpty() -> SearchScreenUiState.Loaded(pokemons.value)
        else -> SearchScreenUiState.Empty
    }
}
