package com.mx.gonz.flow.architecture.domain.api

import com.mx.gonz.flow.architecture.domain.entity.Pokemon

interface RemotePokemonApi {
    suspend fun getPokemonById(id: Int): Pokemon
}
