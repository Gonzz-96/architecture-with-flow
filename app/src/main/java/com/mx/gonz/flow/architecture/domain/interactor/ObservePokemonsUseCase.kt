package com.mx.gonz.flow.architecture.domain.interactor

import com.mx.gonz.flow.architecture.domain.api.LocalPokemonApi
import com.mx.gonz.flow.architecture.domain.api.RemotePokemonApi
import com.mx.gonz.flow.architecture.domain.entity.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.random.Random

class ObservePokemonsUseCase(
    private val remotePokemonApi: RemotePokemonApi,
    private val localPokemonApi: LocalPokemonApi
) {
    operator fun invoke(): Flow<List<Pokemon>> =
        localPokemonApi.getFlowOfLocalPokemons()
}
