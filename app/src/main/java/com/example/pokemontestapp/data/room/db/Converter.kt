package com.example.pokemontestapp.data.room.db

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromTypesList(value: List<String>): String {
        return value.joinToString(",")
    }

    @TypeConverter
    fun toTypesList(value: String): List<String> {
        return value.split(',')
    }
}