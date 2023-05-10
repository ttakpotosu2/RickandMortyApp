package com.example.rickandmortyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortyapp.data.local.characters_daos.CharactersResultsDao
import com.example.rickandmortyapp.data.local.characters_daos.CharactersResultsRemoteKeysDao
import com.example.rickandmortyapp.data.local.episodes_daos.EpisodesResultsDao
import com.example.rickandmortyapp.data.local.episodes_daos.EpisodesResultsRemoteKeysDao
import com.example.rickandmortyapp.data.local.locations_daos.LocationsResultDao
import com.example.rickandmortyapp.data.local.locations_daos.LocationsResultsRemoteKeysDao
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity
import com.example.rickandmortyapp.domain.model.EpisodesResultsEntity
import com.example.rickandmortyapp.domain.model.LocationResultEntity

@Database(
    entities = [
        EpisodesResultsEntity::class,
        EpisodesResultRemoteKeys::class,
        LocationResultEntity::class,
        LocationResultsRemoteKeys::class,
        CharacterResultsEntity::class,
        CharactersResultsRemoteKeys::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(SourceTypeConverter::class)
abstract class RickAndMortyAppResultsDatabase : RoomDatabase() {

    //Episodes Entity
    abstract fun episodesResultsDao(): EpisodesResultsDao
    abstract fun episodesResultsRemoteKeysDao(): EpisodesResultsRemoteKeysDao

    // Locations Entity
    abstract fun locationsResultDao(): LocationsResultDao
    abstract fun locationsResultsRemoteKeysDao(): LocationsResultsRemoteKeysDao

    //Characters Entity
    abstract fun charactersResultsDao(): CharactersResultsDao
    abstract fun charactersRemoteKeysDao(): CharactersResultsRemoteKeysDao
}