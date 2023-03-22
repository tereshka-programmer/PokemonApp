package com.example.pokemontestapp.domain.repository

import androidx.paging.PagingData
import com.example.pokemontestapp.domain.entities.Pokemon
import com.example.pokemontestapp.domain.entities.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface PokemonsRepository {

    fun getPokemons(): Flow<PagingData<Pokemon>>

    suspend fun getPokemonDetails(id: Long): PokemonDetails

}