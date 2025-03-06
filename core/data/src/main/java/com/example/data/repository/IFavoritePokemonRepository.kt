package com.example.data.repository

import com.example.data.database.Pokemon

interface IFavoritePokemonRepository {
    fun getFavoritePokemons(): List<Pokemon>
    fun insertFavoritePokemon(pokemon: Pokemon)
}
