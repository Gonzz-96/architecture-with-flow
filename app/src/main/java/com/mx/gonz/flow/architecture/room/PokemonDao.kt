package com.mx.gonz.flow.architecture.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemons ORDER BY id ASC")
    fun getAllPokemons(): Flow<List<RoomPokemon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewPokemon(pokemon: RoomPokemon)

    @Query("DELETE FROM pokemons")
    suspend fun deleteDatabaseContent()
}
