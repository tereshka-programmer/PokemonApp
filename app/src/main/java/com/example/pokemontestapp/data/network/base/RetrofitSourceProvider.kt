package com.example.pokemontestapp.data.network.base

import com.example.pokemontestapp.data.network.pokemons.PokemonsSource
import com.example.pokemontestapp.data.network.pokemons.RetrofitPokemonsSource

class RetrofitSourceProvider(
    private val config: RetrofitConfig
) {

    fun getPokemonsSource(): PokemonsSource {
        return RetrofitPokemonsSource(config)
    }

}