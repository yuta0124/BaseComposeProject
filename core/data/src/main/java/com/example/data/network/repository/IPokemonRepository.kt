package com.example.data.network.repository

import arrow.core.Either
import com.example.data.network.pokemon.response.PokemonsResponse
import com.example.model.AppError

interface IPokemonRepository {
    /**
     * ポケモン一覧取得
     */
    suspend fun getPokemons(
        limit: Int?,
        offset: Int?,
    ): Either<AppError, PokemonsResponse>
}
