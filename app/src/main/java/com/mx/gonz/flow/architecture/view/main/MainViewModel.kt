package com.mx.gonz.flow.architecture.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mx.gonz.flow.architecture.domain.entity.Pokemon
import com.mx.gonz.flow.architecture.domain.interactor.ObservePokemonsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn

class MainViewModel(
    private val observePokemons: ObservePokemonsUseCase
) : ViewModel() {

    val pokemons = liveData {
        observePokemons(viewModelScope)
            .flowOn(Dispatchers.IO)
            .distinctUntilChanged()
            .collect {
                emit(it)
            }
    }

    override fun onCleared() {
        try {
            viewModelScope.coroutineContext.cancel()
        } catch (e: Throwable) {

        }

        super.onCleared()
    }
}
