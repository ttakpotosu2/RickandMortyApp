package com.example.rickandmortyapp.data.paging

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmortyapp.data.remote.RickAndMortyApi
import com.example.rickandmortyapp.data.local.CharactersResultsRemoteKeys
import com.example.rickandmortyapp.data.local.RickAndMortyAppResultsDatabase
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val rickAndMortyAppResultsDatabase: RickAndMortyAppResultsDatabase
) : RemoteMediator<Int, CharacterResultsEntity>() {

    private val charactersResultsDao = rickAndMortyAppResultsDatabase.charactersResultsDao()
    private val charactersResultsRemoteKeysDao = rickAndMortyAppResultsDatabase.charactersRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterResultsEntity>,
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.next?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prev
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.next
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = rickAndMortyApi.getAllCharacters(currentPage.toString())

            val allEpisodes = rickAndMortyApi.getAllEpisodes(currentPage.toString())

            val episodesIds = response.results.flatMap { it.episodes }.mapNotNull { Uri.parse(it).lastPathSegment }
            val episodesResponse = rickAndMortyApi.getMultipleEpisodes(episodesIds.joinToString(separator = ","))

            val endOfPaginationReached = response.results.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            val remainingEpisodes = allEpisodes.results - episodesResponse

            rickAndMortyAppResultsDatabase.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    charactersResultsDao.deleteCharacters()
//                    charactersResultsRemoteKeysDao.deleteCharactersRemoteKeys()
//                }
                val keys = response.results.map { character ->
                    CharactersResultsRemoteKeys(
                        id = character.id,
                        prev = prevPage,
                        next = nextPage
                    )
                }
                charactersResultsRemoteKeysDao.addCharactersRemoteKeys(remoteKeys = keys)
                charactersResultsDao.addCharacters(
                    characters = response.results.map { it.toCharacterEntity() }
                )
                rickAndMortyAppResultsDatabase
                    .episodesResultsDao()
                    .addEpisodes(episodesResponse.map { it.toEpisodeEntity() })

                rickAndMortyAppResultsDatabase
                    .episodesResultsDao()
                    .updateEpisodes(remainingEpisodes.map { it.toEpisodeEntity() }
                )
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            e.printStackTrace()
            return MediatorResult.Error(e)
        }
    }

    private fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterResultsEntity>
    ): CharactersResultsRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {
                charactersResultsRemoteKeysDao.getCharactersRemoteKeys(id = it)
            }
        }
    }

    private fun getRemoteKeyForFirstItem(
        state: PagingState<Int, CharacterResultsEntity>
    ): CharactersResultsRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { charactersResultsRemoteKeysDao.getCharactersRemoteKeys(id = it.id) }
    }

    private fun getRemoteKeyForLastItem(
        state: PagingState<Int, CharacterResultsEntity>
    ): CharactersResultsRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { charactersResultsRemoteKeysDao.getCharactersRemoteKeys(id = it.id) }
    }
}