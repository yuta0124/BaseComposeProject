package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.FavoritePokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideFavoritePokemonDatabase(@ApplicationContext context: Context): FavoritePokemonDatabase =
        Room.databaseBuilder(
            context = context,
            klass = FavoritePokemonDatabase::class.java,
            name = "favorite-pokemon-database",
        ).build()
}
