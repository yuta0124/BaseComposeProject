package com.example.model

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

data class Pokemons(
    val count: Int,
    val next: String?,
    val pokemons: PersistentList<Pokemon>,
)

data class Pokemon(
    val name: String,
    val url: String,
    val isFavorite: Boolean,
) {
    companion object
}

fun Pokemon.Companion.fakes(): PersistentList<Pokemon> = (0 until 10).map {
    Pokemon(
        name = "name$it",
        url = "https://placehold.jp/150x150.png",
        isFavorite = true,
    )
}.toPersistentList()
