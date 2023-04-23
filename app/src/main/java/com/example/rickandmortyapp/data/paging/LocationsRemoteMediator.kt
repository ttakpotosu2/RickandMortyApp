package com.example.rickandmortyapp.data.paging

import android.net.Uri
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmortyapp.data.local.LocationResultsRemoteKeys
import com.example.rickandmortyapp.data.local.RickAndMortyAppResultsDatabase
import com.example.rickandmortyapp.domain.model.LocationResultEntity
import com.example.rickandmortyapp.data.remote.RickAndMortyApi
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class LocationsRemoteMediator @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val rickAndMortyAppResultsDatabase: RickAndMortyAppResultsDatabase
): RemoteMediator<Int, LocationResultEntity>() {

    private val locationResultsDao = rickAndMortyAppResultsDatabase.locationsResultDao()
    private val locationResultsRemoteKeysDao = rickAndMortyAppResultsDatabase.locationsResultsRemoteKeysDao()

    override suspend fun load( 
        loadType: LoadType,
        state: PagingState<Int, LocationResultEntity>
    ): MediatorResult {
        return try {
            val currentPage = when(loadType){
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
            val response = rickAndMortyApi.getAllLocations(currentPage.toString())
            // complete list of xters from api
            val allCharacters = rickAndMortyApi.getAllCharacters(currentPage.toString())

            val residentsIds = response.results.flatMap { it.residents }.mapNotNull { Uri.parse(it).lastPathSegment }
            val residentsResponse = rickAndMortyApi.getMultipleCharacters(residentsIds.joinToString(separator = ","))

            val endOfPaginationReached = response.results.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            // xter dto list from api minus list used in database
            val remainingCharacters = allCharacters.results - residentsResponse

            rickAndMortyAppResultsDatabase.withTransaction {
                if (loadType == LoadType.REFRESH){
                    locationResultsDao.deleteLocations()
                    locationResultsRemoteKeysDao.deleteLocationsRemoteKeys()
                }
                val keys = response.results.map { location ->
                    LocationResultsRemoteKeys(
                        id = location.id,
                        prev = prevPage,
                        next = nextPage
                    )
                }
                locationResultsRemoteKeysDao.addLocationsRemoteKeys(remoteKeys = keys)
                locationResultsDao.addLocations(
                    locations = response.results.map {
                        it.toLocationEntity()
                    }
                )
                rickAndMortyAppResultsDatabase.charactersResultsDao().addCharacters(
                    residentsResponse.map { it.toCharacterEntity() }
                )
                rickAndMortyAppResultsDatabase.charactersResultsDao().updateCharacters(
                    remainingCharacters.map{ it.toCharacterEntity() }
                )
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }

    private fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, LocationResultEntity>
    ): LocationResultsRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {
                locationResultsRemoteKeysDao.getLocationsRemoteKeys(id = it)
            }
        }
    }

    private fun getRemoteKeysForFirstItem(
        state: PagingState<Int, LocationResultEntity>
    ): LocationResultsRemoteKeys?{
        return state.pages.firstOrNull { it.data.isNotEmpty()}?.data?.firstOrNull()
            ?.let { locationResultsRemoteKeysDao.getLocationsRemoteKeys(id = it.id) }
    }

    private fun getRemoteKeysForLastItem(
        state: PagingState<Int, LocationResultEntity>
    ): LocationResultsRemoteKeys?{
        return state.pages.lastOrNull { it.data.isNotEmpty()}?.data?.lastOrNull()
            ?.let { locationResultsRemoteKeysDao.getLocationsRemoteKeys(id = it.id) }
    }
}