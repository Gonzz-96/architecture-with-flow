package com.mx.gonz.flow.architecture.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class RoomPokemon(
    @PrimaryKey val id: Int,
    val name: String
)
