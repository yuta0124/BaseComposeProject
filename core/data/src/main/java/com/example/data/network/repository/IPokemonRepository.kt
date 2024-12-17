package com.example.data.network.repository

import androidx.compose.runtime.Composable
import com.example.model.Pokemons
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    /**
     * ポケモン一覧取得
     */
    suspend fun getPokemons(
        limit: Int?,
        offset: Int?,
    ): Flow<Pokemons>
}

@Composable
fun localPokemonRepository(): IPokemonRepository {
    return LocalRepositories.current[IPokemonRepository::class] as IPokemonRepository
}
