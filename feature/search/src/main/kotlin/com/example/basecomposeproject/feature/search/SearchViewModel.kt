package com.example.basecomposeproject.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.optics.optics
import com.example.data.database.PokemonTable
import com.example.data.repository.IFavoritePokemonRepository
import com.example.data.repository.IPokemonRepository
import com.example.model.Pokemon
import com.example.utils.extension.toPokemon
import com.example.utils.extension.toPokemonTable
import com.example.utils.extension.toPokemons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@optics
data class UiState(
    val pokemons: PersistentList<Pokemon> = persistentListOf(),
    val isLoading: Boolean = true,
) {
    companion object
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val pokemonRepository: IPokemonRepository,
    private val favoritePokemonRepository: IFavoritePokemonRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private var favoritePokemonNames: PersistentList<String> = persistentListOf()

    init {
        viewModelScope.launch {
            favoritePokemonNames =
                favoritePokemonRepository.getFavoritePokemons()
                    .map(PokemonTable::toPokemon)
                    .map { it.name }
                    .toPersistentList()

            fetchPokemons()
        }
    }

    fun onAction(intent: SearchIntent) = when (intent) {
        SearchIntent.Refresh -> refreshPokemons()

        is SearchIntent.SwitchFavorite -> {
            switchFavorite(intent.pokemon)
        }
    }

    private suspend fun fetchPokemons(limit: Int? = null, offset: Int? = null) {
        pokemonRepository.getPokemons(
            limit = limit,
            offset = offset,
        ).fold(
            ifLeft = { _ ->
                // TODO: エラーハンドリング
            },
            ifRight = { response ->
                _uiState.update { state ->
                    val pokemons = response.toPokemons().pokemons.map { pokemon ->
                        pokemon.copy(isFavorite = favoritePokemonNames.contains(pokemon.name))
                    }

                    UiState.pokemons.modify(state) { state.pokemons.addAll(pokemons) }
                }
            }
        ).run {
            _uiState.update {
                UiState.isLoading.modify(it) { false }
            }
        }
    }

    private fun refreshPokemons() {
        viewModelScope.launch {
            // TODO: limit, offsetの指定
            fetchPokemons()
        }
    }

    private fun switchFavorite(pokemon: Pokemon) {
        insertPokemonInDatabase(pokemon.toPokemonTable())
        val newPokemons = _uiState.value.pokemons.map { state ->
            if (state.name == pokemon.name) {
                state.copy(isFavorite = !state.isFavorite)
            } else {
                state
            }
        }.toPersistentList()
        _uiState.update { UiState.pokemons.modify(it) { newPokemons } }
    }

    private fun insertPokemonInDatabase(pokemon: PokemonTable) {
        viewModelScope.launch {
            favoritePokemonRepository.insertFavoritePokemon(pokemon)
        }
    }
}
