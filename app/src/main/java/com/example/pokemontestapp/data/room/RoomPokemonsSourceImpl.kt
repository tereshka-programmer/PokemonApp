package com.example.pokemontestapp.data.room

import com.example.pokemontestapp.data.room.pokemons.PokemonDetailsDao
import com.example.pokemontestapp.data.room.pokemons.RoomPokemonsSource
import com.example.pokemontestapp.data.room.pokemons.entities.PokemonDetailsDbEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomPokemonsSourceImpl @Inject constructor(
    private val pokemonDetailsDao: PokemonDetailsDao
): RoomPokemonsSource {

    override suspend fun getPokemonDetailsEntity(id: Long): PokemonDetailsDbEntity  {
        return pokemonDetailsDao.getPokemonDetailById(id)
    }

    override suspend fun insertPokemonDetailsEntity(pokemonDetailsDbEntity: PokemonDetailsDbEntity) {
        pokemonDetailsDao.savePokemonDetails(pokemonDetailsDbEntity)
    }
}