package com.example.rickandmortyapp.data.paging

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmortyapp.data.local.EpisodesResultRemoteKeys
import com.example.rickandmortyapp.data.local.RickAndMortyAppResultsDatabase
import com.example.rickandmortyapp.data.remote.RickAndMortyApi
import com.example.rickandmortyapp.domain.model.EpisodesResultsEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class EpisodesRemoteMediator @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val rickAndMortyAppResultsDatabase: RickAndMortyAppResultsDatabase
) : RemoteMediator<Int, EpisodesResultsEntity>() {

    private val episodesResultsDao = rickAndMortyAppResultsDatabase.episodesResultsDao()
    private val episodesResultsRemoteKeysDao =
        rickAndMortyAppResultsDatabase.episodesResultsRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EpisodesResultsEntity>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.next?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prev
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.next
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val response = rickAndMortyApi.getAllEpisodes(currentPage.toString())
            val characterIds = response.results.map { it.characters }
                .mapNotNull { Uri.parse(it.toString()).lastPathSegment }
            val charactersResponse =
                rickAndMortyApi.getMultipleCharacters(characterIds.joinToString(separator = ","))
            val endOfPaginationReached = response.results.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            rickAndMortyAppResultsDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    episodesResultsDao.deleteEpisodes()
                    episodesResultsRemoteKeysDao.deleteEpisodesRemoteKeys()
                }
                val keys = response.results.map { episode ->
                    EpisodesResultRemoteKeys(
                        id = episode.id,
                        prev = prevPage,
                        next = nextPage
                    )
                }
                episodesResultsRemoteKeysDao.addEpisodesRemoteKeys(remoteKeys = keys)
                episodesResultsDao.addEpisodes(
                    episodes = response.results.map {
                        it.toEpisodeEntity()
                    }
                )
                rickAndMortyAppResultsDatabase.charactersResultsDao()
                    .addCharacters(charactersResponse.map { it.toCharacterEntity() })
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, EpisodesResultsEntity>
    ): EpisodesResultRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {
                episodesResultsRemoteKeysDao.getEpisodesRemoteKeys(id = it)
            }
        }
    }

    private fun getRemoteKeysForFirstItem(
        state: PagingState<Int, EpisodesResultsEntity>
    ): EpisodesResultRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { episodesResultsRemoteKeysDao.getEpisodesRemoteKeys(id = it.id) }
    }

    private fun getRemoteKeysForLastItem(
        state: PagingState<Int, EpisodesResultsEntity>
    ): EpisodesResultRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { episodesResultsRemoteKeysDao.getEpisodesRemoteKeys(id = it.id) }
    }
}