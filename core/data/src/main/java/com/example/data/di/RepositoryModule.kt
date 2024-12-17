package com.example.data.di

import com.example.data.network.repository.IPokemonRepository
import com.example.data.network.repository.impl.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @RepositoryQualifier
    @IntoMap
    @ClassKey(IPokemonRepository::class)
    abstract fun bindPokemonRepository(repository: PokemonRepository): Any
}
