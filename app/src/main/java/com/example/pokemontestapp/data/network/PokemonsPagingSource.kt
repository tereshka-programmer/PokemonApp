package com.example.pokemontestapp.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemontestapp.data.network.pokemons.PokemonsSource
import com.example.pokemontestapp.domain.entities.Pokemon
import com.example.pokemontestapp.utils.Constants
import javax.inject.Inject


class PokemonsPagingSource @Inject constructor(
    private val pokemonsSource: PokemonsSource
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(Constants.NETWORK_LIMIT) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val offset = params.key ?: 0

        return try {
            val pokemons = pokemonsSource.getPokemons(offset, params.loadSize)

            return LoadResult.Page(
                data = pokemons.toPokemonsList(),
                prevKey = if (offset == 0) null else offset - Constants.NETWORK_LIMIT,
                nextKey = if (pokemons.results.size == params.loadSize) offset + params.loadSize else null
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }
}