package com.example.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonTable(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "url") val url: String?,
)
