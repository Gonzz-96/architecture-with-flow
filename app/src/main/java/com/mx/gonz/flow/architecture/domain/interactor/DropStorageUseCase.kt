package com.mx.gonz.flow.architecture.domain.interactor

import com.mx.gonz.flow.architecture.domain.api.LocalPokemonApi

class DropStorageUseCase(
    private val localPokemonApi: LocalPokemonApi
) {
    suspend operator fun invoke() {
        localPokemonApi.deleteDatabaseContent()
    }
}
