package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokemonTable::class], version = 1)
abstract class FavoritePokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
