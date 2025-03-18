package com.example.utils.extension

import com.example.data.database.PokemonTable
import com.example.model.Pokemon

fun Pokemon.toPokemonTable(): PokemonTable = PokemonTable(
    name = this.name,
    url = this.url,
)
