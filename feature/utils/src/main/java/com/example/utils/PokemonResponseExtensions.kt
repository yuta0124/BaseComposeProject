package com.example.utils

import com.example.data.network.pokemon.response.PokemonsResponse
import com.example.model.Pokemon
import com.example.model.Pokemons
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

fun PokemonsResponse.toPokemons(): Pokemons = Pokemons(
    count = count,
    next = next,
    pokemons = results?.map {
        Pokemon(name = it.name, url = it.url, isFavorite = false)
    }?.toPersistentList() ?: persistentListOf(),
)
