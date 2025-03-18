package com.example.basecomposeproject.feature.favorites

import com.example.data.database.PokemonTable
import com.example.model.Pokemon

fun PokemonTable.toPokemon(): Pokemon = Pokemon(
    name = this.name,
    url = this.url ?: "",
    isFavorite = true,
)
