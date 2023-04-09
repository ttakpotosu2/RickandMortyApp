package com.example.rickandmortyapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickandmortyapp.presentation.ResidentsItem
import com.example.rickandmortyapp.presentation.screens.states.EpisodeState
import com.example.rickandmortyapp.presentation.screens.viewModels.EpisodeViewModel
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun EpisodeDetailScreen(
    viewModel: EpisodeViewModel = hiltViewModel()
) {
    val detail = viewModel.episode.value
    val scroll = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF121010))
    ) {
        when (detail) {
            is EpisodeState.Success -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(scroll),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = detail.episode.name,
                        style = TextStyle(
                            fontSize = 40.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = detail.episode.airDate,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = detail.episode.episode,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp
                    ) {
                        detail.episode.characters.forEach {character ->
                            ResidentsItem(
                                resident = character,
                                onItemClick = {}
                            )
                        }
                    }
                }
            }
            is EpisodeState.Error -> {
                Text(text = "This is an Error Message")
            }
            is EpisodeState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}