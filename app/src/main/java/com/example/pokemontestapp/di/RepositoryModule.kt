package com.example.pokemontestapp.di

import com.example.pokemontestapp.data.repositoryImpl.PokemonsRepositoryImpl
import com.example.pokemontestapp.domain.repository.PokemonsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPokemonsRepository(
        pokemonsRepositoryImpl: PokemonsRepositoryImpl
    ) : PokemonsRepository

}