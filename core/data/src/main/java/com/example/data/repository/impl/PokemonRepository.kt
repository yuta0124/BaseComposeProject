package com.example.data.repository.impl

import arrow.core.Either
import com.example.data.network.pokemon.PokemonApi
import com.example.data.network.pokemon.response.PokemonsResponse
import com.example.data.repository.IPokemonRepository
import com.example.data.toAppError
import com.example.model.AppError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PokemonRepository(
    private val pokemonApi: PokemonApi,
    private val ioDispatcher: CoroutineDispatcher,
) : IPokemonRepository {
    override suspend fun getPokemons(
        limit: Int?,
        offset: Int?
    ): Either<AppError, PokemonsResponse> = withContext(ioDispatcher) {
        pokemonApi.getPokemons(limit, offset).mapLeft {
            it.toAppError()
        }
    }
}
