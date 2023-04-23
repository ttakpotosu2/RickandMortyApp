package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.data.local.RickAndMortyAppResultsDatabase
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity
import com.example.rickandmortyapp.domain.model.EpisodesResultsEntity
import javax.inject.Inject

data class CharacterAndEpisodes(
    val character: CharacterResultsEntity,
    val episodes: List<EpisodesResultsEntity>
)

class CharacterRepository @Inject constructor(
    private val rickAndMortyAppResultsDatabase: RickAndMortyAppResultsDatabase
)  {

    suspend fun getCharacter(characterId: String): CharacterAndEpisodes {
        val character = rickAndMortyAppResultsDatabase
            .charactersResultsDao()
            .getCharacterById(characterId.toInt())
        val episodes = rickAndMortyAppResultsDatabase
            .episodesResultsDao()
            .getEpisodeFromCharacterReturnEpisode(character.episodes)

        return CharacterAndEpisodes(
            character = character,
            episodes = episodes
        )
    }
}