package com.example.rickandmortyapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.presentation.LocationCard
import com.example.rickandmortyapp.presentation.navigation.Screen
import com.example.rickandmortyapp.presentation.screens.viewModels.LocationsViewModel

@Composable
fun LocationScreen(
    navController: NavHostController,
    locationsViewModel: LocationsViewModel = hiltViewModel()
) {

    val location = locationsViewModel.getAllLocations.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121010))
    ) {
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }
        Text(
            text = stringResource(id = R.string.location_page_text_one),
            style = TextStyle(
                fontSize = 70.sp,
                color = Color.White
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ){
            items(location){location ->
                if (location != null) {
                    LocationCard(
                        location = location,
                        onItemClick = {navController.navigate(
                            Screen.LocationDetailScreen.route + "/${location.id}"
                            )
                        }
                    )
                }
            }
        }
    }
}