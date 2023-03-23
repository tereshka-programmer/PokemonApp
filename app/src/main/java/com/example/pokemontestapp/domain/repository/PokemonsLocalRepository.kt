package com.example.pokemontestapp.domain.repository

import com.example.pokemontestapp.domain.entities.PokemonDetails

interface PokemonsLocalRepository {

    suspend fun getPokemonDetails(id: Long): PokemonDetails

}