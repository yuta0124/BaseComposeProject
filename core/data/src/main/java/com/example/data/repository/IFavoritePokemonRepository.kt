package com.example.data.repository

import com.example.data.database.Pokemon
import kotlinx.collections.immutable.PersistentList

interface IFavoritePokemonRepository {
    suspend fun getFavoritePokemons(): PersistentList<Pokemon>
    suspend fun insertFavoritePokemon(pokemon: Pokemon)
}
