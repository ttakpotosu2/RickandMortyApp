package com.example.rickandmortyapp.presentation.screens.states

import com.example.rickandmortyapp.data.dtos.episoes_dtos.EpisodeDto

sealed class EpisodeState {
    object Loading: EpisodeState()
    data class Success(val episode: EpisodeDto): EpisodeState()
    class Error(val message: String? = null) : EpisodeState()
}
