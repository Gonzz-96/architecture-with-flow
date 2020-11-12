package com.mx.gonz.flow.architecture.view.main

import androidx.lifecycle.*
import com.mx.gonz.flow.architecture.domain.interactor.DropStorageUseCase
import com.mx.gonz.flow.architecture.domain.interactor.GetRemotePokemonsUseCase
import com.mx.gonz.flow.architecture.domain.interactor.ObservePokemonsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel(
    private val observePokemons: ObservePokemonsUseCase,
    private val dropStorage: DropStorageUseCase,
    private val getPokemons: GetRemotePokemonsUseCase
) : ViewModel() {

    val pokemons = observePokemons()
        .flowOn(Dispatchers.IO)
        .distinctUntilChanged()
        .asLiveData(viewModelScope.coroutineContext)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun resetStorage() = viewModelScope.launch {
        dropStorage()
    }

    fun getRemotePokemons() = viewModelScope.launch {
        _isLoading.value = true
        getPokemons()
        _isLoading.value = false
    }

    override fun onCleared() {
        try {
            viewModelScope.coroutineContext.cancel()
        } catch (e: Throwable) {

        }

        super.onCleared()
    }
}
