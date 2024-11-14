package com.example.data.network.repository.impl

import com.example.data.NetworkService
import com.example.data.network.pokemon.PokemonApi
import com.example.data.network.pokemon.response.PokemonsResponse
import com.example.data.network.repository.IPokemonRepository
import com.example.model.Pokemon
import com.example.model.Pokemons
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(
    private val networkService: NetworkService,
    private val pokemonApi: PokemonApi,
) : IPokemonRepository {
    // TODO: Either
    override suspend fun getPokemons(limit: Int?, offset: Int?): Pokemons {
        return networkService {
            pokemonApi.getPokemons(limit, offset)
        }.toPokemons()
    }
}

private fun PokemonsResponse.toPokemons(): Pokemons = Pokemons(
    count = count,
    next = next,
    pokemons = results?.map {
        Pokemon(name = it.name, url = it.url)
    }?.toPersistentList() ?: persistentListOf(),
)
