package com.example.rickandmortyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortyapp.data.local.locations_daos.LocationsResultDao
import com.example.rickandmortyapp.data.local.locations_daos.LocationsResultsRemoteKeysDao
import com.example.rickandmortyapp.domain.model.LocationResultEntity

@Database(
    entities = [LocationResultEntity::class, LocationResultsRemoteKeys::class],
    version = 1
)
@TypeConverters(SourceTypeConvertor::class)
abstract class LocationsResultsDatabase: RoomDatabase() {

    abstract fun locationsResultDao(): LocationsResultDao
    abstract fun locationsResultsRemoteKeysDao(): LocationsResultsRemoteKeysDao
}