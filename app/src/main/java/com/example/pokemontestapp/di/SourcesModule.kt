package com.example.pokemontestapp.di

import com.example.pokemontestapp.data.network.pokemons.PokemonsSource
import com.example.pokemontestapp.data.network.pokemons.RetrofitPokemonsSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SourcesModule {

    @Binds
    abstract fun bindPokemonsSource(
        retrofitPokemonsSource: RetrofitPokemonsSource
    ): PokemonsSource

}