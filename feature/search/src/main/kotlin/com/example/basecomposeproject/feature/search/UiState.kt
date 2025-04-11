package com.example.basecomposeproject.feature.search

import arrow.optics.optics
import com.example.model.Pokemon
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@optics
data class UiState(
    val pokemons: PersistentList<Pokemon> = persistentListOf(),
    val isLoading: Boolean = true,
) {
    companion object
}
