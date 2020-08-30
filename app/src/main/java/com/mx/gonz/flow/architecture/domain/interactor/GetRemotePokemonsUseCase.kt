package com.mx.gonz.flow.architecture.domain.interactor

import com.mx.gonz.flow.architecture.domain.api.LocalPokemonApi
import com.mx.gonz.flow.architecture.domain.api.RemotePokemonApi
import kotlinx.coroutines.delay
import kotlin.random.Random

class GetRemotePokemonsUseCase(
    private val remotePokemonApi: RemotePokemonApi,
    private val localPokemonApi: LocalPokemonApi
) {
    suspend operator fun invoke() {
        for (i in 0..9) {
            val pokemonId = Random.nextInt(1, 300)
            // get pokemon from network
            remotePokemonApi.getPokemonById(pokemonId).let {
                // then introduce it in the db
                localPokemonApi.addNewPokemon(it)
            }
            // simulate heavy operation
            delay(100)
        }
    }
}
