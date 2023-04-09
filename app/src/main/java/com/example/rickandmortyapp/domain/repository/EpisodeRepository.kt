package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.data.dtos.episoes_dtos.EpisodeDto
import com.example.rickandmortyapp.data.remote.RickAndMortyApi
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
) {

    suspend fun getEpisode(episodeId: String): EpisodeDto{
        return rickAndMortyApi.getEpisodeById(episodeId)
    }
}