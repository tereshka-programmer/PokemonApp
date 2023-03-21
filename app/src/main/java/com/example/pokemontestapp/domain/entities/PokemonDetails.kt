package com.example.pokemontestapp.domain.entities

data class PokemonDetails(
    val id: Long,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<String>,
    val imageUrl: String
)