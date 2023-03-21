package com.example.pokemontestapp.data.network.pokemons

import com.example.pokemontestapp.data.network.pokemons.entities.PokemonDetailsResponse
import com.example.pokemontestapp.data.network.pokemons.entities.PokemonsResponse

interface PokemonsSource {

    suspend fun getPokemons(): PokemonsResponse

    suspend fun getPokemonDetails(pokemonId: Long): PokemonDetailsResponse
}