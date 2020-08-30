package com.mx.gonz.flow.architecture.di

import com.mx.gonz.flow.architecture.domain.interactor.ObservePokemonsUseCase
import org.koin.dsl.module

val appModule = module {
    factory {
        ObservePokemonsUseCase(get(), get())
    }
}