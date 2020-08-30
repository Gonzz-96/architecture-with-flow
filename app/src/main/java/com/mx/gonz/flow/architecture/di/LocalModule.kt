package com.mx.gonz.flow.architecture.di

import com.mx.gonz.flow.architecture.data.local.LocalPokemonApiImpl
import com.mx.gonz.flow.architecture.domain.api.LocalPokemonApi
import org.koin.dsl.module

val localModule = module {
    single<LocalPokemonApi> {
        LocalPokemonApiImpl(get())
    }
}
