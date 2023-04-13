package com.example.rickandmortyapp.presentation.screens.viewModels

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyapp.data.local.RickAndMortyAppResultsDatabase
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity
import com.example.rickandmortyapp.data.paging.CharactersRemoteMediator
import com.example.rickandmortyapp.data.remote.RickAndMortyApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
@HiltViewModel
class CharactersViewModel @Inject constructor(
    rickAndMortyApi: RickAndMortyApi,
    rickAndMortyAppResultsDatabase: RickAndMortyAppResultsDatabase
): ViewModel() {
    private val pagingSourceFactory = { rickAndMortyAppResultsDatabase.charactersResultsDao().getCharacters() }

    val getAllCharacters: Flow<PagingData<CharacterResultsEntity>> = Pager(
        config = PagingConfig(
            pageSize = 50
        ),
        remoteMediator = CharactersRemoteMediator(
            rickAndMortyApi = rickAndMortyApi,
            rickAndMortyAppResultsDatabase = rickAndMortyAppResultsDatabase
        ),
        pagingSourceFactory = pagingSourceFactory
    ).flow.flowOn(Dispatchers.IO)
}