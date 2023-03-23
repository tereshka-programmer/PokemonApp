package com.example.pokemontestapp.data.network.pokemons.entities

import com.example.pokemontestapp.data.room.pokemons.entities.PokemonDbEntity
import com.example.pokemontestapp.domain.entities.Pokemon
import com.example.pokemontestapp.utils.Constants.URL_SIZE

data class PokemonsResponse(
    val next: String,
    val previous: String?,
    val results: List<Result>
) {

    fun toPokemonDbEntityList(): List<PokemonDbEntity> = results.map {
        PokemonDbEntity(
            id = getIdFromLink(it.url),
            name = it.name
        )
    }
    fun toPokemonsList(): List<Pokemon> = results.map {
        Pokemon(
            id = getIdFromLink(it.url),
            name = it.name
        )
    }

    private fun getIdFromLink(link: String): Long {
        val id = link.drop(URL_SIZE).dropLast(1)
        return id.toLong()
    }
}