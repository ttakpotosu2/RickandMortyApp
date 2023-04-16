package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.data.local.RickAndMortyAppResultsDatabase
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val rickAndMortyAppResultsDatabase: RickAndMortyAppResultsDatabase
)  {

    suspend fun getCharacter(characterId: String): CharacterResultsEntity {
        return rickAndMortyAppResultsDatabase.charactersResultsDao().getCharacterById(characterId.toInt())
    }
}