package com.example.data.network.repository

import com.example.model.Pokemons

interface IPokemonRepository {
    /**
     * ポケモン一覧取得
     */
    suspend fun getPokemons(
        limit: Int?,
        offset: Int?,
    ): Pokemons
}
