package com.example.basecomposeproject.feature.search

import com.example.model.Pokemon

sealed interface SearchIntent {
    data object Resume : SearchIntent
    data object Refresh : SearchIntent
    data class SwitchFavorite(val pokemon: Pokemon) : SearchIntent
}
