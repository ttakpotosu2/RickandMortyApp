package com.example.rickandmortyapp.presentation.screens.states

import com.example.rickandmortyapp.domain.repository.CharacterAndEpisodes

sealed class CharacterState {
    object Loading: CharacterState()
    data class Success(val character: CharacterAndEpisodes): CharacterState()
    class Error(val message: String? = null) : CharacterState()
}