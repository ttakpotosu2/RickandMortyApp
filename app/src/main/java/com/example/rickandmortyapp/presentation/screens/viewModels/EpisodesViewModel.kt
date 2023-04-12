package com.example.rickandmortyapp.presentation.screens.viewModels

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyapp.data.local.RickAndMortyAppResultsDatabase
import com.example.rickandmortyapp.data.paging.EpisodesRemoteMediator
import com.example.rickandmortyapp.data.remote.RickAndMortyApi
import com.example.rickandmortyapp.domain.model.EpisodesResultsEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
@HiltViewModel
class EpisodesViewModel @Inject constructor(
    rickAndMortyApi: RickAndMortyApi,
    private val rickAndMortyAppResultsDatabase: RickAndMortyAppResultsDatabase
): ViewModel() {
    private val pagingSource = {
        rickAndMortyAppResultsDatabase.episodesResultsDao().getEpisodes()
    }

    val getAllEpisodes : Flow<PagingData<EpisodesResultsEntity>> = Pager(
        config = PagingConfig(
            pageSize = 50
        ),
        remoteMediator = EpisodesRemoteMediator(
            rickAndMortyApi = rickAndMortyApi,
            rickAndMortyAppResultsDatabase = rickAndMortyAppResultsDatabase
        ),
        pagingSourceFactory = pagingSource
    ).flow
}