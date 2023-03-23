package com.example.pokemontestapp.data.room.pokemons

import com.example.pokemontestapp.data.room.pokemons.entities.PokemonDetailsDbEntity

interface RoomPokemonsSource {

    suspend fun getPokemonDetailsEntity(id: Long): PokemonDetailsDbEntity

    suspend fun insertPokemonDetailsEntity(pokemonDetailsDbEntity: PokemonDetailsDbEntity)

}