package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemontable")
    suspend fun getFavoritePokemons(): List<PokemonTable>

    @Insert
    suspend fun insertFavoritePokemon(pokemon: PokemonTable)
}
