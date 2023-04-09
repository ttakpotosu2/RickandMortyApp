package com.example.rickandmortyapp.domain.repository

import com.example.rickandmortyapp.data.dtos.locations_dtos.LocationDto
import com.example.rickandmortyapp.data.remote.RickAndMortyApi
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
) {
    suspend fun getLocation(locationId: String): LocationDto {
        return rickAndMortyApi.getLocationById(locationId)
    }
}