package com.example.basecomposeproject.feature.favorites

import com.example.model.Pokemon

sealed interface FavoritesIntent {
    data object GetFavoritePokemons : FavoritesIntent
    data class DeleteFavoritePokemon(val pokemon: Pokemon) : FavoritesIntent
}
