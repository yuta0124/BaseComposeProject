package com.example.basecomposeproject.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basecomposeproject.core.common.opticsCompose
import com.example.basecomposeproject.feature.favorites.FavoritesIntent.DeleteFavoritePokemon
import com.example.basecomposeproject.feature.favorites.FavoritesIntent.GetFavoritePokemons
import com.example.data.database.PokemonTable
import com.example.data.repository.IFavoritePokemonRepository
import com.example.model.Pokemon
import com.example.utils.extension.toPokemon
import com.example.utils.extension.toPokemonTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class test(
    val name: String,
    val isFavorite: Boolean,
)



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
        GetFavoritePokemons -> fetchFavoritePokemons()
        is DeleteFavoritePokemon -> deletePokemonInDB(intent.pokemon)
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

    private fun deletePokemonInDB(pokemon: Pokemon) {
        viewModelScope.launch {
            favoritePokemonRepository.deleteFvoritePokemon(pokemon.toPokemonTable())
            fetchFavoritePokemons()
        }
    }
}
