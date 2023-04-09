package com.example.rickandmortyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.rickandmortyapp.data.local.episodes_daos.EpisodesResultsDao
import com.example.rickandmortyapp.data.local.episodes_daos.EpisodesResultsRemoteKeysDao
import com.example.rickandmortyapp.domain.model.EpisodesResultsEntity

@Database(
    entities = [EpisodesResultsEntity::class, EpisodesResultRemoteKeys::class],
    version = 1
)
@TypeConverters(SourceTypeConvertor::class)
abstract class EpisodesResultsDatabase: RoomDatabase() {

    abstract fun episodesResultsDao(): EpisodesResultsDao
    abstract fun episodesResultsRemoteKeysDao(): EpisodesResultsRemoteKeysDao
}