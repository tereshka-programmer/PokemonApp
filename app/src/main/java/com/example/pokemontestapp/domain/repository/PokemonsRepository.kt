package com.example.pokemontestapp.domain.repository

import com.example.pokemontestapp.domain.entities.Pokemon
import com.example.pokemontestapp.domain.entities.PokemonDetails

interface PokemonsRepository {

    suspend fun getPokemons(): List<Pokemon>

    suspend fun getPokemonDetails(id: Long): PokemonDetails

}