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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.presentation.EpisodesItem
import com.example.rickandmortyapp.presentation.ResidentsItem
import com.example.rickandmortyapp.presentation.navigation.Screen
import com.example.rickandmortyapp.presentation.screens.states.CharacterState
import com.example.rickandmortyapp.presentation.screens.states.LocationState
import com.example.rickandmortyapp.presentation.screens.viewModels.CharacterDetailViewModel
import com.example.rickandmortyapp.presentation.screens.viewModels.CharactersViewModel
import com.example.rickandmortyapp.presentation.screens.viewModels.LocationViewModel
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun LocationDetailScreen(
//    navController: NavHostController,
    viewModel: LocationViewModel = hiltViewModel(),
) {

    val detail = viewModel.location.value
    val scroll = rememberScrollState()
    
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF121010))
    ) {
        when (detail) {
            is LocationState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(scroll),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = detail.location.locationName,
                        style = TextStyle(
                            fontSize = 40.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Type: ${detail.location.type}",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Dimension: ${detail.location.dimension}",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.location_detail_page_text_one),
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    )
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp
                    ) {
                        detail.location.residents.forEach {resident ->
                            ResidentsItem(
                                resident = resident,
                                onItemClick = {}
                            )
                        }
                    }
                }
            }
            is LocationState.Error -> {
                Text(text = "This is an Error Message")
            }
            is LocationState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}