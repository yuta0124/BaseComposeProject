package com.example.data.repository.impl

import com.example.data.database.Pokemon
import com.example.data.database.PokemonDao
import com.example.data.repository.IFavoritePokemonRepository
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FavoritePokemonRepository(
    private val pokemonDao: PokemonDao,
    private val ioDispatcher: CoroutineDispatcher,
) : IFavoritePokemonRepository {
    override suspend fun getFavoritePokemons(): PersistentList<Pokemon> =
        withContext(ioDispatcher) {
            pokemonDao.getFavoritePokemons().toPersistentList()
        }

    override suspend fun insertFavoritePokemon(pokemon: Pokemon) = withContext(ioDispatcher) {
        pokemonDao.insertFavoritePokemon(pokemon)
    }
}
