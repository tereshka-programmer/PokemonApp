package com.example.pokemontestapp.data.repositoryImpl

import com.example.pokemontestapp.data.network.pokemons.PokemonsSource
import com.example.pokemontestapp.domain.entities.Pokemon
import com.example.pokemontestapp.domain.entities.PokemonDetails
import com.example.pokemontestapp.domain.repository.PokemonsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonsRepositoryImpl @Inject constructor(
    private val pokemonsSource: PokemonsSource
): PokemonsRepository{

    override suspend fun getPokemons(): List<Pokemon> {
        return pokemonsSource.getPokemons().toPokemonsList()
    }

    override suspend fun getPokemonDetails(id: Long): PokemonDetails {
        return pokemonsSource.getPokemonDetails(id).toPokemonDetails()
    }

}