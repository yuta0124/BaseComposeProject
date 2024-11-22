package com.example.data.network.repository

import arrow.core.Either
import com.example.model.AppError
import com.example.model.Pokemons

interface IPokemonRepository {
    /**
     * ポケモン一覧取得
     */
    suspend fun getPokemons(
        limit: Int?,
        offset: Int?,
    ): Either<AppError, Pokemons>
}
