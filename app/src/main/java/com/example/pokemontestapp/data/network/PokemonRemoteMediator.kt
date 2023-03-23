package com.example.pokemontestapp.data.network

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pokemontestapp.data.network.pokemons.PokemonsSource
import com.example.pokemontestapp.data.room.pokemons.PokemonDao
import com.example.pokemontestapp.data.room.pokemons.entities.PokemonDbEntity
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@ExperimentalPagingApi
class PokemonRemoteMediator @AssistedInject constructor(
    private val pokemonsSource: PokemonsSource,
    private val pokemonDao: PokemonDao
) : RemoteMediator<Int, PokemonDbEntity>() {

    private var pageIndex = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonDbEntity>,
    ): MediatorResult {
        pageIndex = getPageIndex(loadType) ?:
            return MediatorResult.Success(endOfPaginationReached = true)

        val limit = state.config.pageSize
        val offset = pageIndex*limit

        return try {
            val pokemons = pokemonsSource.getPokemons(offset, limit).toPokemonDbEntityList()
            if (loadType == LoadType.REFRESH) {
                pokemonDao.refresh(pokemons)
            } else {
                pokemonDao.save(pokemons)
            }
            MediatorResult.Success(
                endOfPaginationReached = pokemons.size < limit
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }

    @AssistedFactory
    interface Factory {
        fun create(): PokemonRemoteMediator
    }

}