package com.example.rickandmortyapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
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
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.presentation.LocationCard
import com.example.rickandmortyapp.presentation.screens.viewModels.LocationsViewModel
import com.example.rickandmortyapp.presentation.spacing

@Composable
fun LocationScreen(
    navigateUp: () -> Unit,
    openLocationDetailScreen: (Int) -> Unit,
    locationsViewModel: LocationsViewModel = hiltViewModel()
) {

    val location = locationsViewModel.getAllLocations.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121010))
    ) {
        IconButton(
            onClick = { navigateUp() },
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
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
                .padding(horizontal = MaterialTheme.spacing.medium)
        )
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            contentPadding = PaddingValues(vertical = MaterialTheme.spacing.medium)
        ) {
            items(
                count = location.itemCount,
                key = location.itemKey(),
                contentType = location.itemContentType(
                )
            ) { index ->
                val item = location[index]
                if (item != null) {
                    LocationCard(
                        location = item,
                        onItemClick = {
                            openLocationDetailScreen(item.id)
                        }
                    )
                }
            }
        }
    }
}