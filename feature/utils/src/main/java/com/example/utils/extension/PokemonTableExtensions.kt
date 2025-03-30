package com.example.utils.extension

import com.example.data.database.PokemonTable
import com.example.model.Pokemon

fun PokemonTable.toPokemon(): Pokemon = Pokemon(
    name = this.name,
    url = this.url ?: "",
    isFavorite = true,
)
