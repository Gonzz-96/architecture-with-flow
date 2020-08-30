package com.mx.gonz.flow.architecture.di

import androidx.room.Room
import com.mx.gonz.flow.architecture.room.PokemonDB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val POKEMON_DATABASE_NAME = "POKEMON_DATABASE"

val roomModule = module {
    // pokemon databse
    single {
        Room.databaseBuilder(androidContext(), PokemonDB::class.java, POKEMON_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    // pokemon dao
    single {
        get<PokemonDB>().getPokemonDao()
    }
}
