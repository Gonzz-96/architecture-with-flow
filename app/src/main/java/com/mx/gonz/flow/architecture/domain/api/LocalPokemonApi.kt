package com.mx.gonz.flow.architecture.domain.api

import com.mx.gonz.flow.architecture.domain.entity.Pokemon
import kotlinx.coroutines.flow.Flow

interface LocalPokemonApi {
    fun getFlowOfLocalPokemons(): Flow<List<Pokemon>>
    suspend fun addNewPokemon(pokemon: Pokemon)
    suspend fun deleteDatabaseContent()
}
