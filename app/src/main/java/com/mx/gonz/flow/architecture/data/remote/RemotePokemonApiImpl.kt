package com.mx.gonz.flow.architecture.data.remote

import com.mx.gonz.flow.architecture.domain.api.RemotePokemonApi
import com.mx.gonz.flow.architecture.domain.entity.Pokemon

class RemotePokemonApiImpl(
    private val pokemonService: PokemonService
): RemotePokemonApi {
    override suspend fun getPokemonById(id: Int): Pokemon =
        pokemonService.getPokemonById(id)
}
