package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.data.local.RickAndMortyAppResultsDatabase
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity
import com.example.rickandmortyapp.domain.model.LocationResultEntity
import javax.inject.Inject

data class LocationAndCharacters(
    val location: LocationResultEntity,
    val characters: List<CharacterResultsEntity>
)

class LocationRepository @Inject constructor(
    private val rickAndMortyAppResultsDatabase: RickAndMortyAppResultsDatabase,
) {
    suspend fun getLocation(locationId: Int): LocationAndCharacters {
        val location = rickAndMortyAppResultsDatabase.locationsResultDao().getSingleLocation(locationId)
        val characters = rickAndMortyAppResultsDatabase.charactersResultsDao()
            .getCharacterFromLocationReturnCharacters(location.residents)

        return LocationAndCharacters(
            location = location,
            characters = characters,
        )
    }
}