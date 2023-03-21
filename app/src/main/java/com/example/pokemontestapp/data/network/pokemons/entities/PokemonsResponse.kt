package com.example.pokemontestapp.data.network.pokemons.entities

import com.example.pokemontestapp.domain.entities.Pokemon

data class PokemonsResponse(
    val next: String,
    val previous: String?,
    val results: List<Result>
) {
    fun toPokemonsList(): List<Pokemon> = results.map {
        Pokemon(
            id = getIdFromLink(it.url),
            name = it.name
        )
    }

    private fun getIdFromLink(link: String): Long {
        val id = link.drop(34).dropLast(1)
        return id.toLong()
    }
}