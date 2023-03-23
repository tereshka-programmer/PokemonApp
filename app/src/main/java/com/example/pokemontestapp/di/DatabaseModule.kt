package com.example.pokemontestapp.di

import android.content.Context
import androidx.room.Room
import com.example.pokemontestapp.data.room.pokemons.PokemonDao
import com.example.pokemontestapp.data.room.db.AppDatabase
import com.example.pokemontestapp.data.room.pokemons.PokemonDetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun providePokemonDetailsDao(appDatabase: AppDatabase): PokemonDetailsDao {
        return appDatabase.getPokemonDetailsDao()
    }

    @Provides
    fun providePokemonDao(appDatabase: AppDatabase): PokemonDao {
        return appDatabase.getPokemonDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "database.db").build()
    }
}