package com.example.rickandmortyapp.presentation.screens.states

import com.example.rickandmortyapp.data.dtos.locations_dtos.LocationDto

sealed class LocationState {
    object Loading: LocationState()
    data class Success(val location: LocationDto): LocationState()
    class Error(val message: String? = null) : LocationState()
}
