package com.example.pokemontestapp.data.room.pokemons

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemontestapp.data.room.pokemons.entities.PokemonDetailsDbEntity

@Dao
interface PokemonDetailsDao {

    @Query("SELECT * FROM pokemon_details WHERE id = :id")
    suspend fun getPokemonDetailById(id: Long): PokemonDetailsDbEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemonDetails(pokemon: PokemonDetailsDbEntity)

}