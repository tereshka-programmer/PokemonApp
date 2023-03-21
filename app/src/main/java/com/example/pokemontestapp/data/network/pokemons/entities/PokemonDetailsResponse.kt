package com.example.pokemontestapp.data.network.pokemons.entities

data class PokemonDetailsResponse(
    val id: Long,
    val height: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
    val weight: Int
)