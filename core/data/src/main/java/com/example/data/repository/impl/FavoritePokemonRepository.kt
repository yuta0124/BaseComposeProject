package com.example.data.repository.impl

import com.example.data.database.Pokemon
import com.example.data.database.PokemonDao
import com.example.data.repository.IFavoritePokemonRepository

class FavoritePokemonRepository(
    private val pokemonDao: PokemonDao,
) : IFavoritePokemonRepository {
    override fun getFavoritePokemons(): List<Pokemon> = pokemonDao.getFavoritePokemons()
    override fun insertFavoritePokemon(pokemon: Pokemon) = pokemonDao.insertFavoritePokemon(pokemon)
}
