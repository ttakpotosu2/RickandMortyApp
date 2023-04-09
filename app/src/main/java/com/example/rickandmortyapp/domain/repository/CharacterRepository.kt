package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.data.dtos.characters_dtos.CharacterDto
import com.example.rickandmortyapp.data.local.CharacterResultsDatabase
import com.example.rickandmortyapp.data.remote.RickAndMortyApi
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val characterResultsDatabase: CharacterResultsDatabase
)  {

    suspend fun getCharacter(characterId: String): CharacterResultsEntity {
        return characterResultsDatabase.charactersResultsDao().getCharacterById(characterId.toInt())
    }
}