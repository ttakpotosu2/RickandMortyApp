package com.example.rickandmortyapp.presentation.screens.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.domain.repository.LocationRepository
import com.example.rickandmortyapp.presentation.screens.states.LocationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: LocationRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _location = mutableStateOf<LocationState>(LocationState.Loading)
    val location : State<LocationState> = _location

    init {
        savedStateHandle.get<String>("locationId")?.let { locationById(it) }
    }


    private fun locationById(locationId: String) {
        viewModelScope.launch {
            val result = repository.getLocation(locationId)
            _location.value = LocationState.Success(result)
        }
    }
}