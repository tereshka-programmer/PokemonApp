package com.example.pokemontestapp.data.room

import com.example.pokemontestapp.data.room.db.wrapSQLiteException
import com.example.pokemontestapp.data.room.pokemons.PokemonDetailsDao
import com.example.pokemontestapp.data.room.pokemons.RoomPokemonsSource
import com.example.pokemontestapp.data.room.pokemons.entities.PokemonDetailsDbEntity
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomPokemonsSourceImpl @Inject constructor(
    private val pokemonDetailsDao: PokemonDetailsDao
): RoomPokemonsSource {

    override suspend fun getPokemonDetailsEntity(id: Long): PokemonDetailsDbEntity = wrapSQLiteException(Dispatchers.IO) {
        pokemonDetailsDao.getPokemonDetailById(id)
    }

    override suspend fun insertPokemonDetailsEntity(pokemonDetailsDbEntity: PokemonDetailsDbEntity) = wrapSQLiteException(Dispatchers.IO){
        pokemonDetailsDao.savePokemonDetails(pokemonDetailsDbEntity)
    }
}