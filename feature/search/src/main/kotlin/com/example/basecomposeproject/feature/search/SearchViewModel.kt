package com.example.basecomposeproject.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basecomposeproject.feature.search.SearchIntent.Refresh
import com.example.basecomposeproject.feature.search.SearchIntent.Resume
import com.example.basecomposeproject.feature.search.SearchIntent.SwitchFavorite
import com.example.data.database.PokemonTable
import com.example.data.repository.IFavoritePokemonRepository
import com.example.data.repository.IPokemonRepository
import com.example.model.Pokemon
import com.example.utils.extension.toPokemonTable
import com.example.utils.extension.toPokemons
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val pokemonRepository: IPokemonRepository,
    private val favoritePokemonRepository: IFavoritePokemonRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val pokemons = MutableStateFlow(persistentListOf<Pokemon>())

    init {
        viewModelScope.launch {
            fetchPokemons()

            combine(
                pokemons,
                favoritePokemonRepository.favoritePokemonNames
            ) { oldPokemons, favoritePokemonNames ->
                oldPokemons
                    .map { it.copy(isFavorite = favoritePokemonNames.contains(it.name)) }
                    .toPersistentList()
            }.collect { newPokemons ->
                _uiState.update { it.copy(pokemons = newPokemons) }
            }
        }
    }

    fun onAction(intent: SearchIntent) = when (intent) {
        Resume -> getFavoritePokemons()
        Refresh -> refreshPokemons()

        is SwitchFavorite -> {
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
                pokemons.update { response.toPokemons().pokemons }
            }
        ).run {
            _uiState.update {
                UiState.isLoading.modify(it) { false }
            }
        }
    }

    private fun refreshPokemons() {
        viewModelScope.launch {
            fetchPokemons(limit = 20, offset = uiState.value.pokemons.size.plus(1))
        }
    }

    private fun switchFavorite(pokemon: Pokemon) {
        if (pokemon.isFavorite) {
            delelteFavoritePokemonInDB(pokemon.toPokemonTable())
        } else {
            insertPokemonInDB(pokemon.toPokemonTable())
        }

        val newPokemons = _uiState.value.pokemons.map { state ->
            if (state.name == pokemon.name) {
                state.copy(isFavorite = !state.isFavorite)
            } else {
                state
            }
        }.toPersistentList()
        _uiState.update { UiState.pokemons.modify(it) { newPokemons } }
    }

    private fun insertPokemonInDB(pokemon: PokemonTable) {
        viewModelScope.launch {
            favoritePokemonRepository.insertFavoritePokemon(pokemon)
        }
    }

    private fun delelteFavoritePokemonInDB(pokemon: PokemonTable) {
        viewModelScope.launch {
            favoritePokemonRepository.deleteFvoritePokemon(pokemon)
        }
    }

    private fun getFavoritePokemons() {
        viewModelScope.launch {
            favoritePokemonRepository.getFavoritePokemons()
        }
    }
}
