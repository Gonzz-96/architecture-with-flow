package com.mx.gonz.flow.architecture.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomPokemon::class], version = 1)
abstract class PokemonDB : RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao
}
