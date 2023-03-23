package com.example.pokemontestapp.data.repositoryImpl

import com.example.pokemontestapp.data.room.pokemons.RoomPokemonsSource
import com.example.pokemontestapp.domain.entities.PokemonDetails
import com.example.pokemontestapp.domain.repository.PokemonsLocalRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonsLocalRepositoryImpl @Inject constructor(
    private val roomPokemonsSource: RoomPokemonsSource
) : PokemonsLocalRepository {

    override suspend fun getPokemonDetails(id: Long): PokemonDetails {
        return roomPokemonsSource.getPokemonDetailsEntity(id).toPokemonDetails()
    }
}