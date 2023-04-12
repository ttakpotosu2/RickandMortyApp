package com.example.rickandmortyapp.data.dtos.characters_dtos

import android.net.Uri
import com.example.rickandmortyapp.domain.model.CharacterResultsEntity
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto( // link from EpisodeDto
    val created: String,
    @SerializedName("episode") val episodes: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    @SerializedName("name") val charactersName: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    @SerializedName("url") val charactersUrl: String
) {
    fun toCharacterEntity(): CharacterResultsEntity {
        return CharacterResultsEntity(
            episodes = episodes.mapNotNull { Uri.parse(it).lastPathSegment },
            gender = gender,
            id = id,
            image = image,
            charactersName = charactersName,
            charactersUrl = charactersUrl,
            status = status,
            origin = origin,
            species = species
        )
    }
}