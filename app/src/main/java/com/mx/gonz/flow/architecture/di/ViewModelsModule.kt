package com.mx.gonz.flow.architecture.di

import com.mx.gonz.flow.architecture.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel {
        MainViewModel(get(), get())
    }
}
