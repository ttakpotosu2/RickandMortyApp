package com.example.rickandmortyapp.data.local.episodes_daos

import androidx.paging.PagingSource
import androidx.room.*
import com.example.rickandmortyapp.data.dtos.episoes_dtos.EpisodeDto
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity
import com.example.rickandmortyapp.domain.model.EpisodesResultsEntity
import com.example.rickandmortyapp.domain.model.LocationResultEntity

@Dao
interface EpisodesResultsDao {

    @Query("SELECT * FROM episodes_results_table")
    fun getEpisodes(): PagingSource<Int, EpisodesResultsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEpisodes(episodes: List<EpisodesResultsEntity>)

    // update table with new episodes
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEpisodes(updateIds: List<EpisodesResultsEntity>)

    @Query("DELETE FROM episodes_results_table")
    suspend fun deleteEpisodes()

    @Query("SELECT * FROM episodes_results_table WHERE id = :episode")
    suspend fun getSingleEpisode(episode: Int): EpisodesResultsEntity

    @Query("SELECT * FROM episodes_results_table WHERE id IN (:ids)")
    suspend fun getEpisodeFromCharacterReturnEpisode (ids: List<String>): List<EpisodesResultsEntity>
}