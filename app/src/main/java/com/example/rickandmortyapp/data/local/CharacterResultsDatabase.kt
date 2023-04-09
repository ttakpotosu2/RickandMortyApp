package com.example.rickandmortyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortyapp.data.local.characters_daos.CharactersResultsDao
import com.example.rickandmortyapp.data.local.characters_daos.CharactersResultsRemoteKeysDao
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity

@Database(
    entities = [CharacterResultsEntity::class, CharactersResultsRemoteKeys::class],
    version = 1
)
@TypeConverters(SourceTypeConvertor::class)
abstract class CharacterResultsDatabase: RoomDatabase() {

    abstract fun charactersResultsDao(): CharactersResultsDao
    abstract fun charactersRemoteKeysDao(): CharactersResultsRemoteKeysDao
}