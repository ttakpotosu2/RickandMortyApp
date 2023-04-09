package com.example.rickandmortyapp.data.local.episodes_daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyapp.data.local.EpisodesResultRemoteKeys

@Dao
interface EpisodesResultsRemoteKeysDao {

    @Query("SELECT * FROM episodes_results_remote_keys_table WHERE id = :id")
    fun getEpisodesRemoteKeys(id: Int): EpisodesResultRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEpisodesRemoteKeys(remoteKeys: List<EpisodesResultRemoteKeys>)

    @Query("DELETE FROM episodes_results_remote_keys_table")
    suspend fun deleteEpisodesRemoteKeys()
}