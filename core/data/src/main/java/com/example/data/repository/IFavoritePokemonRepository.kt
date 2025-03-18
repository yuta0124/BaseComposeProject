package com.example.data.repository

import com.example.data.database.PokemonTable
import kotlinx.collections.immutable.PersistentList

interface IFavoritePokemonRepository {
    suspend fun getFavoritePokemons(): PersistentList<PokemonTable>
    suspend fun insertFavoritePokemon(pokemon: PokemonTable)
}
