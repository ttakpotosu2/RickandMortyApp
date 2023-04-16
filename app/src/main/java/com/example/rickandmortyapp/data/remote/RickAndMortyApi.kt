package com.example.rickandmortyapp.data.remote

import com.example.rickandmortyapp.data.PagedData
import com.example.rickandmortyapp.data.dtos.characters_dtos.CharacterDto
import com.example.rickandmortyapp.data.dtos.episoes_dtos.EpisodeDto
import com.example.rickandmortyapp.data.dtos.locations_dtos.LocationDto
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character/")
    suspend fun getAllCharacters(@Query("page") page: String): PagedData<CharacterDto>

    @GET("character/{characterId}")
    suspend fun getCharacterById(@Path("characterId") characterId: List<String>): List<CharacterResultsEntity> // called straight from repository

    @GET("location")
    suspend fun getAllLocations(@Query("page") page: String): PagedData<LocationDto>

    @GET("location/{locationId}")
    suspend fun getLocationById(@Path("locationId") locationId: String): LocationDto

    @GET("episode")
    suspend fun getAllEpisodes(@Query("page") page: String): PagedData<EpisodeDto>

    @GET("episode/{episodeId}")
    suspend fun getEpisodeById(@Path("episodeId") episodeId: String): EpisodeDto

    @GET("episode/{episode}")
    suspend fun getMultipleEpisodes(@Path("episode") episode: String): List<EpisodeDto>

    @GET("character/{character}")
    suspend fun getMultipleCharacters(@Path("character") character: String): List<CharacterDto>
}