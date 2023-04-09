package com.example.rickandmortyapp.data.dtos.characters_dtos

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerializedName("name") val locationName: String,
    @SerializedName("url")val locationUrl: String
)