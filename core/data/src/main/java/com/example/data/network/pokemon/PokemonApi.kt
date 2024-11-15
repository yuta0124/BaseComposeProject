package com.example.data.network.pokemon

import com.example.data.network.pokemon.response.PokemonsResponse
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
    ): PokemonsResponse
}
