package com.example.basecomposeproject.feature.favorites

import arrow.optics.optics
import com.example.model.Pokemon
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@optics
data class UiState(
    val isLoading: Boolean = true,
    val pokemons: PersistentList<Pokemon> = persistentListOf(),
) {
    companion object
}
