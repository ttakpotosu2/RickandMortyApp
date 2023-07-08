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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.presentation.EpisodeCard
import com.example.rickandmortyapp.presentation.screens.viewModels.EpisodesViewModel

@Composable
fun EpisodesScreen(
    navigateUp: () -> Unit,
    openEpisodeDetailScreen: (Int) -> Unit,
    episodesViewModel: EpisodesViewModel = hiltViewModel()
) {
    val episodes = episodesViewModel.getAllEpisodes.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121010))
    ) {
        IconButton(
            onClick = { navigateUp() },
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
            text = stringResource(id = R.string.episode_page_text_one),
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
        ) {
            items(
                count = episodes.itemCount,
                key = episodes.itemKey(),
                contentType = episodes.itemContentType(
                )
            ) { index ->
                val item = episodes[index]
                if (item != null) {
                    EpisodeCard(
                        episode = item,
                        onItemClick = { openEpisodeDetailScreen(item.id) }
                    )
                }
            }
            episodes.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(vertical = 200.dp),
                                    color = Color.White
                                )
                            }

                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally),
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}