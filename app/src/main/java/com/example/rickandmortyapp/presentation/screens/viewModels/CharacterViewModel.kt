package com.example.rickandmortyapp.presentation.screens.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.domain.repository.CharacterRepository
import com.example.rickandmortyapp.presentation.screens.states.CharacterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _character = mutableStateOf<CharacterState>(CharacterState.Loading)
    val character: State<CharacterState> = _character

    init {
        savedStateHandle.get<String>("characterId")?.let { characterById(it) }
    }

    private fun characterById(characterId: String){
        viewModelScope.launch {
            val result = repository.getCharacter(characterId)
            _character.value = CharacterState.Success(character = result)
        }
    }
}