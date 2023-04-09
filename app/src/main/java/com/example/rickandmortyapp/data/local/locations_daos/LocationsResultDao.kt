package com.example.rickandmortyapp.data.local.locations_daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity
import com.example.rickandmortyapp.domain.model.LocationResultEntity

@Dao
interface LocationsResultDao {

    @Query("SELECT * FROM locations_result_table")
    fun getLocations(): PagingSource<Int, LocationResultEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLocations(locations: List<LocationResultEntity>)

    @Query("DELETE FROM locations_result_table")
    suspend fun deleteLocations()
}