package com.example.pokemontestapp.data.room.pokemons.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokemontestapp.domain.entities.Pokemon

@Entity(tableName = "pokemon")
data class PokemonDbEntity(
    @PrimaryKey val id: Long,
    val name: String
) {
    fun toPokemon(): Pokemon = Pokemon(
        id = id,
        name = name
    )
}