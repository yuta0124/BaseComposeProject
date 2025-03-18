package com.example.data.di

import com.example.data.database.FavoritePokemonDatabase
import com.example.data.database.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DaoModule {
    @Provides
    @Singleton
    fun providePokemonDao(database: FavoritePokemonDatabase): PokemonDao = database.pokemonDao()
}
