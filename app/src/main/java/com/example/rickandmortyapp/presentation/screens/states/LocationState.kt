package com.example.rickandmortyapp.presentation.screens.states

import com.example.rickandmortyapp.domain.repository.LocationAndCharacters

sealed class LocationState {
    object Loading: LocationState()
    data class Success(val location: LocationAndCharacters): LocationState()
    class Error(val message: String? = null) : LocationState()
}
