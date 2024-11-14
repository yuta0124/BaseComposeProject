package com.example.data.network.pokemon.response

import kotlinx.serialization.Serializable

@Serializable
data class PokemonsResponse(
    val count: Int,
    val next: String?,
    val previous: Int?,
    val results: List<Result>?
)
