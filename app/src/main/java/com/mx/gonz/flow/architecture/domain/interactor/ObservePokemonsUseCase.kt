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
    operator fun invoke(coroutineScope: CoroutineScope): Flow<Pokemon> {
        // launch new coroutine to get a pokemon every 2 seconds
        coroutineScope.launch(Dispatchers.IO) {
            for (i in 0 until 100) {
                val pokemonId = Random.nextInt(1, 300)
                remotePokemonApi.getPokemonById(pokemonId).let {
                    localPokemonApi.addNewPokemon(it)
                }
                delay(2_000)
            }
        }

        return localPokemonApi.observeLocalPokemons()
    }
}
