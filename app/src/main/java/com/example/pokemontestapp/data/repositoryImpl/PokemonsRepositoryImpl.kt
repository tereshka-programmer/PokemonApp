package com.example.pokemontestapp.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokemontestapp.data.network.PokemonsPagingSource
import com.example.pokemontestapp.data.network.pokemons.PokemonsSource
import com.example.pokemontestapp.domain.entities.Pokemon
import com.example.pokemontestapp.domain.entities.PokemonDetails
import com.example.pokemontestapp.domain.repository.PokemonsRepository
import com.example.pokemontestapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonsRepositoryImpl @Inject constructor(
    private val pokemonsSource: PokemonsSource
): PokemonsRepository{

    override fun getPokemons(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_LIMIT,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PokemonsPagingSource(pokemonsSource) }
        ).flow
    }

    override suspend fun getPokemonDetails(id: Long): PokemonDetails {
        return pokemonsSource.getPokemonDetails(id).toPokemonDetails()
    }

}