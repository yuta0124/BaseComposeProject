package com.example.basecomposeproject.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.optics.optics
import com.example.basecomposeproject.core.common.opticsCompose
import com.example.data.database.PokemonTable
import com.example.data.repository.IFavoritePokemonRepository
import com.example.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@optics
data class UiState(
    val isLoading: Boolean = true,
    val pokemons: PersistentList<Pokemon> = persistentListOf(),
) {
    companion object
}

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritePokemonRepository: IFavoritePokemonRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        fetchFavoritePokemons()
    }

    fun onAction(intent: FavoritesIntent) = when (intent) {
        FavoritesIntent.Refresh -> fetchFavoritePokemons()
        is FavoritesIntent.SwitchFavorite -> switchFavorite(intent.pokemon)
    }

    private fun fetchFavoritePokemons() {
        viewModelScope.launch {
            val result: PersistentList<Pokemon> =
                favoritePokemonRepository.getFavoritePokemons().map(PokemonTable::toPokemon)
                    .toPersistentList()

            _uiState.update { state ->
                opticsCompose(
                    state,
                    { UiState.isLoading.set(it, false) },
                    { UiState.pokemons.set(it, result) },
                )
            }
        }
    }

    @Suppress("UnusedParameter")
    private fun switchFavorite(pokemon: Pokemon) {
        // TODO: お気に入り状態切り替え処理
    }
}
