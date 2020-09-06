package com.mx.gonz.flow.architecture.data.local

import com.mx.gonz.flow.architecture.domain.entity.Pokemon
import com.mx.gonz.flow.architecture.room.PokemonDao
import com.mx.gonz.flow.architecture.room.RoomPokemon
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.hamcrest.CoreMatchers.`is` as iss

class LocalPokemonApiImplTest {

    private lateinit var mockPokemonDao: PokemonDao
    private lateinit var localPokemonApiImpl: LocalPokemonApiImpl

    @Before
    fun setUp() {
        mockPokemonDao = mock()
        localPokemonApiImpl = LocalPokemonApiImpl(mockPokemonDao)
    }

    @After
    fun tearDown() {
    }


    @Test
    fun `Check local api returns a correct domain pokemon`() = runBlockingTest {
        // given
        val roomPokemon = RoomPokemon(1, "Pokemon")
        val domainPokemon = Pokemon(1, "Pokemon")
        mockPokemonDao = mock {
            on { getAllPokemons() } doReturn flowOf(listOf(roomPokemon))
        }
        localPokemonApiImpl = LocalPokemonApiImpl(mockPokemonDao)

        // when
        val pokemons = localPokemonApiImpl.getFlowOfLocalPokemons().first()

        // then
        verify(mockPokemonDao, times(1)).getAllPokemons()
        assertThat(pokemons, iss(listOf(domainPokemon)))
    }

    @Test
    fun `Check local api drops whole database`() = runBlockingTest {
        // when
        localPokemonApiImpl.deleteDatabaseContent()

        // then
        verify(mockPokemonDao, times(1)).deleteDatabaseContent()
    }

    @Test
    fun `Check local api inserts correct object in database`() = runBlockingTest {
        // given
        val roomPokemon = RoomPokemon(1, "Pokemon")
        val domainPokemon = Pokemon(1, "Pokemon")

        // when
        localPokemonApiImpl.addNewPokemon(domainPokemon)

        // then
        verify(mockPokemonDao, times(1)).addNewPokemon(roomPokemon)
    }
}
