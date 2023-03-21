package com.example.pokemontestapp.data.network.pokemons.entities

import com.example.pokemontestapp.domain.entities.PokemonDetails

data class PokemonDetailsResponse(
    val id: Long,
    val height: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
    val weight: Int
) {
    fun toPokemonDetails(): PokemonDetails = PokemonDetails(
        id = id,
        name = name,
        height = height,
        weight = weight,
        types = types.map { it.type.name },
        imageUrl = sprites.front_default
    )
}