package com.example.pokemontestapp.di

import com.example.pokemontestapp.data.network.pokemons.PokemonsSource
import com.example.pokemontestapp.data.network.pokemons.RetrofitPokemonsSource
import com.example.pokemontestapp.data.room.RoomPokemonsSourceImpl
import com.example.pokemontestapp.data.room.pokemons.RoomPokemonsSource
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

    @Binds
    abstract fun bindRoomPokemonsSource(
        roomPokemonsSourceImpl: RoomPokemonsSourceImpl
    ): RoomPokemonsSource

}