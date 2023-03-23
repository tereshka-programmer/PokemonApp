package com.example.pokemontestapp.data.room.pokemons

import androidx.paging.PagingSource
import androidx.room.*
import com.example.pokemontestapp.data.room.pokemons.entities.PokemonDbEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon")
    fun getPagingSource(): PagingSource<Int, PokemonDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemons: List<PokemonDbEntity>)

    @Query("DELETE FROM pokemon")
    suspend fun clear()

    @Transaction
    suspend fun refresh(pokemons: List<PokemonDbEntity>) {
        clear()
        save(pokemons)
    }
}