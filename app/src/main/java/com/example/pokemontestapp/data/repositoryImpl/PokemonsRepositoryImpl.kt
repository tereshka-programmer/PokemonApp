package com.example.pokemontestapp.data.repositoryImpl

import androidx.paging.*
import com.example.pokemontestapp.data.network.PokemonRemoteMediator
import com.example.pokemontestapp.data.network.PokemonsPagingSource
import com.example.pokemontestapp.data.network.pokemons.PokemonsSource
import com.example.pokemontestapp.data.room.pokemons.PokemonDao
import com.example.pokemontestapp.data.room.pokemons.PokemonDetailsDao
import com.example.pokemontestapp.data.room.pokemons.entities.PokemonDetailsDbEntity
import com.example.pokemontestapp.domain.entities.Pokemon
import com.example.pokemontestapp.domain.entities.PokemonDetails
import com.example.pokemontestapp.domain.repository.PokemonsRepository
import com.example.pokemontestapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalPagingApi
@Singleton
class PokemonsRepositoryImpl @Inject constructor(
    private val pokemonsSource: PokemonsSource,
    private val remoteMediatorFactory: PokemonRemoteMediator.Factory,
    private val pokemonDao: PokemonDao,
    private val pokemonDetailsDao: PokemonDetailsDao
): PokemonsRepository{

    override fun getPokemons(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_LIMIT,
                enablePlaceholders = false
            ),
            remoteMediator = remoteMediatorFactory.create(),
            pagingSourceFactory = { pokemonDao.getPagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { pokemonDbEntity ->
                pokemonDbEntity.toPokemon()
            }

        }
    }

    override suspend fun getPokemonDetails(id: Long): PokemonDetails {
        val details = pokemonsSource.getPokemonDetails(id).toPokemonDetails()
        pokemonDetailsDao.savePokemonDetails(PokemonDetailsDbEntity(
            id = details.id,
            name = details.name,
            height = details.height,
            weight = details.weight,
            image = details.imageUrl,
            types = details.types
        ))
        return details
    }

}