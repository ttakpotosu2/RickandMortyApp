package com.example.rickandmortyapp.presentation.screens.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.domain.repository.EpisodeRepository
import com.example.rickandmortyapp.presentation.screens.states.EpisodeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repository: EpisodeRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _episode = mutableStateOf<EpisodeState>(EpisodeState.Loading)
    val episode: State<EpisodeState> = _episode

    init {
        savedStateHandle.get<String>("episodeId")?.let { episodesById(it) }

    }

    private fun episodesById(episodeId: String){
        viewModelScope.launch {
            val result = repository.getEpisode(episodeId)
            _episode.value = EpisodeState.Success(episode = result)
        }
    }
}