package com.example.data.di

import com.example.basecomposeproject.core.common.network.AppDispatcher
import com.example.basecomposeproject.core.common.network.AppDispatchers
import com.example.data.database.PokemonDao
import com.example.data.network.pokemon.PokemonApi
import com.example.data.repository.IFavoritePokemonRepository
import com.example.data.repository.IPokemonRepository
import com.example.data.repository.impl.FavoritePokemonRepository
import com.example.data.repository.impl.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun providePokemonRepository(
        api: PokemonApi,
        @AppDispatcher(AppDispatchers.IO) ioDispatcher: CoroutineDispatcher,
    ): IPokemonRepository = PokemonRepository(api, ioDispatcher)

    @Provides
    @Singleton
    fun provideFavoritePokemonRepository(pokemonDao: PokemonDao): IFavoritePokemonRepository =
        FavoritePokemonRepository(pokemonDao)
}
