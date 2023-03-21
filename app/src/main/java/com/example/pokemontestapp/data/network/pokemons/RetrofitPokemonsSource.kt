package com.example.pokemontestapp.data.network.pokemons

import com.example.pokemontestapp.data.network.base.BaseRetrofitSource
import com.example.pokemontestapp.data.network.base.RetrofitConfig
import com.example.pokemontestapp.data.network.pokemons.entities.PokemonDetailsResponse
import com.example.pokemontestapp.data.network.pokemons.entities.PokemonsResponse

class RetrofitPokemonsSource(
    config: RetrofitConfig
) : BaseRetrofitSource(config), PokemonsSource{

    private val pokemonsApi = retrofit.create(PokemonsApi::class.java)

    override suspend fun getPokemons(): PokemonsResponse = wrapRetrofitExceptions {
        pokemonsApi.getPokemons()
    }

    override suspend fun getPokemonDetails(pokemonId: Long): PokemonDetailsResponse = wrapRetrofitExceptions{
        pokemonsApi.getPokemonDetails(pokemonId)
    }

}