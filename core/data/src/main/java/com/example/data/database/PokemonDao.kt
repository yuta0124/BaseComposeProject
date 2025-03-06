package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getFavoritePokemons(): List<Pokemon>

    @Insert
    fun insertFavoritePokemon(pokemon: Pokemon)
}
