package com.mx.gonz.flow.architecture.data.remote

import com.mx.gonz.flow.architecture.domain.entity.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): Pokemon
}
