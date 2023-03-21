package com.example.pokemontestapp.data.network.pokemons.entities

data class PokemonsResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)