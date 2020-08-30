package com.mx.gonz.flow.architecture.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.mx.gonz.flow.architecture.domain.interactor.DropStorageUseCase
import com.mx.gonz.flow.architecture.domain.interactor.GetRemotePokemonsUseCase
import com.mx.gonz.flow.architecture.domain.interactor.ObservePokemonsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel(
    private val observePokemons: ObservePokemonsUseCase,
    private val dropStorage: DropStorageUseCase,
    private val getPokemons: GetRemotePokemonsUseCase
) : ViewModel() {

    val pokemons = liveData {
        observePokemons()
            .flowOn(Dispatchers.IO)
            .distinctUntilChanged()
            .collect {
                emit(it)
            }
    }

    fun resetStorage() = viewModelScope.launch {
        dropStorage()
    }

    fun getRemotePokemons() = viewModelScope.launch {
        getPokemons()
    }

    override fun onCleared() {
        try {
            viewModelScope.coroutineContext.cancel()
        } catch (e: Throwable) {

        }

        super.onCleared()
    }
}
