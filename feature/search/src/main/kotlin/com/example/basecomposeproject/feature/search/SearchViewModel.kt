package com.example.basecomposeproject.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.optics.optics
import com.example.data.network.repository.impl.PokemonRepository
import com.example.model.Pokemon
import com.example.utils.toPokemons
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
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        refreshPokemons()
    }

    fun onAction(intent: SearchIntent) = when (intent) {
        SearchIntent.Refresh -> refreshPokemons()

        is SearchIntent.SwitchFavorite -> {
            switchFavorite(intent.name)
        }
    }

    private fun refreshPokemons() {
        viewModelScope.launch {
            pokemonRepository.getPokemons(
                limit = null,
                offset = null,
            ).fold(
                ifLeft = { error ->
                    // TODO: エラーハンドリング
                },
                ifRight = { response ->
                    _uiState.update { UiState.pokemons.modify(it) { response.toPokemons().pokemons } }
                }
            ).run {
                _uiState.update {
                    UiState.isLoading.modify(it) { false }
                }
            }
        }
    }

    // TODO: Roomにて、お気に入り一覧のデータソースを作成、その一覧の特定の名前を更新する
    private fun switchFavorite(name: String) {
        val newPokemons = _uiState.value.pokemons.map { state ->
            if (state.name == name) {
                state.copy(isFavorite = !state.isFavorite)
            } else {
                state
            }
        }.toPersistentList()

        _uiState.update { UiState.pokemons.modify(it) { newPokemons } }
    }
}
