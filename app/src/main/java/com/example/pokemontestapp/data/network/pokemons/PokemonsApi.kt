package com.example.pokemontestapp.data.network.pokemons

import com.example.pokemontestapp.data.network.pokemons.entities.PokemonDetailsResponse
import com.example.pokemontestapp.data.network.pokemons.entities.PokemonsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonsApi {

    @GET("pokemon")
    suspend fun getPokemons(): PokemonsResponse

    @GET("pokemon/{pokemonId}/")
    suspend fun getPokemonDetails(
        @Path("pokemonId") pokemonId: Long
    ): PokemonDetailsResponse

}