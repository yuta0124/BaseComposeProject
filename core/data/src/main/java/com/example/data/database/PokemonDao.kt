package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    suspend fun getFavoritePokemons(): List<Pokemon>

    @Insert
    suspend fun insertFavoritePokemon(pokemon: Pokemon)
}
