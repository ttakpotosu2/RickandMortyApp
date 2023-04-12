package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.data.local.RickAndMortyAppResultsDatabase
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity
import com.example.rickandmortyapp.domain.model.EpisodesResultsEntity
import javax.inject.Inject

data class EpisodeAndCharacters(
    val episode: EpisodesResultsEntity,
    val characters: List<CharacterResultsEntity>
)

class EpisodeRepository @Inject constructor(
    private val rickAndMortyProjectDatabase: RickAndMortyAppResultsDatabase
) {

    suspend fun getEpisode(episodeId: String): EpisodeAndCharacters {
        val episode = rickAndMortyProjectDatabase.episodesResultsDao().getSingleEpisode(episodeId.toInt())
        val characters = rickAndMortyProjectDatabase.charactersResultsDao()
            .getCharacterFromEpisodeReturnCharacters(episode.characters)

        return EpisodeAndCharacters(episode = episode, characters = characters)
    }
}