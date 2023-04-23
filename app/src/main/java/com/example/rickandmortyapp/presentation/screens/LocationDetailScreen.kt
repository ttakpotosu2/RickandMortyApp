package com.example.rickandmortyapp.presentation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity
import com.example.rickandmortyapp.presentation.navigation.Screen
import com.example.rickandmortyapp.presentation.screens.states.LocationState
import com.example.rickandmortyapp.presentation.screens.viewModels.LocationViewModel
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun LocationDetailScreen(
    navController: NavHostController,
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
                        text = detail.location.location.name,
                        style = TextStyle(
                            fontSize = 40.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Type: ${detail.location.location.type}",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Dimension: ${detail.location.location.dimension}",
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
                        mainAxisSpacing = 6.dp,
                        crossAxisSpacing = 6.dp
                    ) {
                        detail.location.characters
                            .forEach { resident -> LocationResidentsItem(resident = resident) {
                                navController.navigate(
                                    Screen.CharacterDetailScreen.route + "/${resident.id}"
                                )
                            }
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

@Composable
fun LocationResidentsItem(
    resident: CharacterResultsEntity?,
    onItemClick: () -> Unit
) {

    val imagePainter = rememberAsyncImagePainter(
        model = resident?.image
    )

    Column(modifier = Modifier
        .border(
            width = 1.dp,
            color = Color.White,
            shape = RoundedCornerShape(12.dp)
        )
        .padding(10.dp)
        .clickable { onItemClick() }
    ){
        Image(painter = imagePainter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(6.dp))
        )
        resident?.charactersName?.let {
            Text(
                text = it,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}