package com.example.rickandmortyapp.presentation.screens.states

import com.example.rickandmortyapp.domain.model.CharacterResultsEntity

sealed class CharacterState {
    object Loading: CharacterState()
    data class Success(val character: CharacterResultsEntity): CharacterState()
    class Error(val message: String? = null) : CharacterState()
}