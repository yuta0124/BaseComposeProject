package com.example.data.repository

import com.example.data.database.PokemonTable
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.StateFlow

interface IFavoritePokemonRepository {
    val favoritePokemonNames: StateFlow<PersistentList<String>>

    suspend fun getFavoritePokemons(): PersistentList<PokemonTable>
    suspend fun insertFavoritePokemon(pokemon: PokemonTable)
    suspend fun deleteFvoritePokemon(pokemon: PokemonTable)
}
