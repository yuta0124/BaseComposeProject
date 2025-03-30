package com.example.data.repository.impl

import com.example.data.database.PokemonDao
import com.example.data.database.PokemonTable
import com.example.data.repository.IFavoritePokemonRepository
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class FavoritePokemonRepository(
    private val pokemonDao: PokemonDao,
    private val ioDispatcher: CoroutineDispatcher,
) : IFavoritePokemonRepository {
    private val _favoritePokemonNames = MutableStateFlow(persistentListOf<String>())
    override val favoritePokemonNames: StateFlow<PersistentList<String>> =
        _favoritePokemonNames.asStateFlow()

    override suspend fun getFavoritePokemons(): PersistentList<PokemonTable> =
        withContext(ioDispatcher) {
            val favoritePokemons = pokemonDao.getFavoritePokemons().toPersistentList()
            _favoritePokemonNames.update { favoritePokemons.map { it.name }.toPersistentList() }

            return@withContext favoritePokemons
        }

    override suspend fun insertFavoritePokemon(pokemon: PokemonTable) = withContext(ioDispatcher) {
        pokemonDao.insertFavoritePokemon(pokemon)
    }

    override suspend fun deleteFvoritePokemon(pokemon: PokemonTable) = withContext(ioDispatcher) {
        pokemonDao.deleteFavoritePokemon(pokemon)
    }
}
