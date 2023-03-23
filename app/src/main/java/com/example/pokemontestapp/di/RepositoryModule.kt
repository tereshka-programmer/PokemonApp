package com.example.pokemontestapp.di

import androidx.paging.ExperimentalPagingApi
import com.example.pokemontestapp.data.repositoryImpl.PokemonsLocalRepositoryImpl
import com.example.pokemontestapp.data.repositoryImpl.PokemonsRepositoryImpl
import com.example.pokemontestapp.domain.repository.PokemonsLocalRepository
import com.example.pokemontestapp.domain.repository.PokemonsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @ExperimentalPagingApi
    @Binds
    abstract fun bindPokemonsRepository(
        pokemonsRepositoryImpl: PokemonsRepositoryImpl
    ) : PokemonsRepository

    @Binds
    abstract fun bindLocalPokemonRepository(
        pokemonsLocalRepositoryImpl: PokemonsLocalRepositoryImpl
    ) : PokemonsLocalRepository
}