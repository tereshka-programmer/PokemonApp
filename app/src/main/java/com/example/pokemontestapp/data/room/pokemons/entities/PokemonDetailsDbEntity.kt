package com.example.pokemontestapp.data.room.pokemons.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokemontestapp.domain.entities.PokemonDetails

@Entity(
    tableName = "pokemon_details"
)
data class PokemonDetailsDbEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val height: Int,
    val weight: Int,
    val image: String,
    val types: List<String>
) {
    fun toPokemonDetails(): PokemonDetails = PokemonDetails(
        id = id,
        name = name,
        height = height,
        weight = weight,
        types = types,
        imageUrl = image
    )
}