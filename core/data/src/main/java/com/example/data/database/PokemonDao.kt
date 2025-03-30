package com.example.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemontable")
    suspend fun getFavoritePokemons(): List<PokemonTable>

    @Insert
    suspend fun insertFavoritePokemon(pokemon: PokemonTable)

    @Delete
    suspend fun deleteFavoritePokemon(pokemon: PokemonTable)
}
