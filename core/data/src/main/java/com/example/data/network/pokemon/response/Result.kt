package com.example.data.network.pokemon.response

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val name: String,
    val url: String,
)
