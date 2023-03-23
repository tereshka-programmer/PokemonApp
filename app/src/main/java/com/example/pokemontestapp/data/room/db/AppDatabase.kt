package com.example.pokemontestapp.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokemontestapp.data.room.pokemons.PokemonDao
import com.example.pokemontestapp.data.room.pokemons.PokemonDetailsDao
import com.example.pokemontestapp.data.room.pokemons.entities.PokemonDbEntity
import com.example.pokemontestapp.data.room.pokemons.entities.PokemonDetailsDbEntity


@Database(
    version = 1,
    entities = [
        PokemonDbEntity::class,
        PokemonDetailsDbEntity::class
    ]
)
@TypeConverters(
    Converter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

    abstract fun getPokemonDetailsDao(): PokemonDetailsDao

}