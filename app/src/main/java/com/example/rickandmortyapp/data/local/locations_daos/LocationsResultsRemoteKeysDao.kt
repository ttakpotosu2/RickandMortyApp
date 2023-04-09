package com.example.rickandmortyapp.data.local.locations_daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyapp.data.local.LocationResultsRemoteKeys

@Dao
interface LocationsResultsRemoteKeysDao {

    @Query("SELECT * FROM location_results_remote_keys_table WHERE id = :id")
    fun getLocationsRemoteKeys(id: Int): LocationResultsRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLocationsRemoteKeys(remoteKeys: List<LocationResultsRemoteKeys>)

    @Query("DELETE FROM location_results_remote_keys_table")
    suspend fun deleteLocationsRemoteKeys()
}