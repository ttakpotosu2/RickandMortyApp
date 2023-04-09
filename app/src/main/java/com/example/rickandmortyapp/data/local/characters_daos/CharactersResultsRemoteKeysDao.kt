package com.example.rickandmortyapp.data.local.characters_daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyapp.data.local.CharactersResultsRemoteKeys

@Dao
interface CharactersResultsRemoteKeysDao {

    @Query("SELECT * FROM characters_results_remote_keys_table WHERE id = :id")
    fun getCharactersRemoteKeys(id: Int): CharactersResultsRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharactersRemoteKeys(remoteKeys: List<CharactersResultsRemoteKeys>)

    @Query("DELETE FROM characters_results_remote_keys_table")
    suspend fun deleteCharactersRemoteKeys()
}