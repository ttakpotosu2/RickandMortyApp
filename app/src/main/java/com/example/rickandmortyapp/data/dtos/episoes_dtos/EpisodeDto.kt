package com.example.rickandmortyapp.data.dtos.episoes_dtos


import com.example.rickandmortyapp.domain.model.EpisodesResultsEntity
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDto(
    @SerializedName("air_date")
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
){
    fun toEpisodeEntity(): EpisodesResultsEntity {
        return EpisodesResultsEntity(
            airDate, created, episode, id, name, url
        )
    }
}