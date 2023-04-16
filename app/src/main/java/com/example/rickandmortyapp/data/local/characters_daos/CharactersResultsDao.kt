package com.example.rickandmortyapp.data.local.characters_daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity

@Dao
interface CharactersResultsDao {

    @Query("SELECT * FROM characters_results_table")
    fun getCharacters(): PagingSource<Int, CharacterResultsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<CharacterResultsEntity>) // this adds xters to table

    @Query("DELETE FROM characters_results_table")
    suspend fun deleteCharacters()

    @Query("SELECT * FROM characters_results_table WHERE id = :id ")
    suspend fun getCharacterById(id: Int): CharacterResultsEntity

    @Query("SELECT * FROM characters_results_table WHERE id IN (:ids)")
    suspend fun getCharacterFromEpisodeReturnCharacters (ids: List<Int>): List<CharacterResultsEntity>

    @Query("SELECT * FROM characters_results_table WHERE id IN (:ids)")
    suspend fun getCharacterFromLocationReturnCharacters (ids: List<Int>): List<CharacterResultsEntity>
}