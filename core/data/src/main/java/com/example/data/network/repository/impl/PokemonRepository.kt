package com.example.data.network.repository.impl

import com.example.basecomposeproject.core.common.network.AppDispatcher
import com.example.basecomposeproject.core.common.network.AppDispatchers
import com.example.data.network.NetworkService
import com.example.data.network.pokemon.PokemonApi
import com.example.data.network.pokemon.response.PokemonsResponse
import com.example.data.network.repository.IPokemonRepository
import com.example.model.AppError
import com.example.model.Pokemon
import com.example.model.Pokemons
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val pokemonApi: PokemonApi,
    private val networkService: NetworkService,
    @AppDispatcher(AppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : IPokemonRepository {
    @Throws(AppError::class)
    override suspend fun getPokemons(
        limit: Int?,
        offset: Int?
    ): Flow<Pokemons> = networkService {
        withContext(ioDispatcher) {
            flowOf(pokemonApi.getPokemons(limit, offset).toPokemons())
        }
    }
}

private fun PokemonsResponse.toPokemons(): Pokemons = Pokemons(
    count = count,
    next = next,
    pokemons = results?.map {
        Pokemon(name = it.name, url = it.url)
    }?.toPersistentList() ?: persistentListOf(),
)
