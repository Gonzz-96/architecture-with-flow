package com.mx.gonz.flow.architecture.data.local

import com.mx.gonz.flow.architecture.domain.api.LocalPokemonApi
import com.mx.gonz.flow.architecture.domain.entity.Pokemon
import com.mx.gonz.flow.architecture.room.PokemonDao
import com.mx.gonz.flow.architecture.room.RoomPokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalPokemonApiImpl(
    private val pokemonDao: PokemonDao
) : LocalPokemonApi {
    override suspend fun observeLocalPokemons(): Flow<Pokemon> =
        pokemonDao.getAllPokemons()
            .map(::toPokemon)

    override suspend fun addNewPokemon(pokemon: Pokemon) {
        pokemon.run {
            RoomPokemon(id, name)
        }.let { pokemonDao.addNewPokemon(it) }
    }

    private fun toPokemon(roomPokemon: RoomPokemon) = Pokemon(
        id = roomPokemon.id,
        name = roomPokemon.name
    )
}
