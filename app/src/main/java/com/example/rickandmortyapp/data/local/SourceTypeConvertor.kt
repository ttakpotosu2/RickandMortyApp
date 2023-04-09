package com.example.rickandmortyapp.data.local

import androidx.room.TypeConverter
import com.example.rickandmortyapp.data.dtos.characters_dtos.Origin
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SourceTypeConvertor {

    @TypeConverter
    fun fromString(source: String): List<String>{
        return Json.decodeFromString(source)
    }

    @TypeConverter
    fun toString(list: List<String>): String{
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun fromOrigin(source: Origin): String{
        return Json.encodeToString(source)
    }

    @TypeConverter
    fun toOrigin(storeOrigin: String): Origin{
        return  Json.decodeFromString(storeOrigin)
    }
}