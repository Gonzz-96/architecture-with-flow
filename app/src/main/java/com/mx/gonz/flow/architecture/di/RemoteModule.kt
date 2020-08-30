package com.mx.gonz.flow.architecture.di

import com.mx.gonz.flow.architecture.data.remote.PokemonService
import com.mx.gonz.flow.architecture.data.remote.RemotePokemonApiImpl
import com.mx.gonz.flow.architecture.domain.api.RemotePokemonApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO move to constants
private const val BASE_URL = "https://pokeapi.co/api/v2/\n"

val remoteModule = module {
    single<PokemonService> {
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonService::class.java)
    }

    single<RemotePokemonApi> {
        RemotePokemonApiImpl(get())
    }
}
