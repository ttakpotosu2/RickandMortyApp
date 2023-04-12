package com.example.rickandmortyapp.presentation.screens.states

import com.example.rickandmortyapp.data.dtos.episoes_dtos.EpisodeDto
import com.example.rickandmortyapp.domain.repository.EpisodeAndCharacters

sealed class EpisodeState {
    object Loading: EpisodeState()
    data class Success(val episode: EpisodeAndCharacters): EpisodeState()
    class Error(val message: String? = null) : EpisodeState()
}
