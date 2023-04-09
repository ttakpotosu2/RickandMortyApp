package com.example.rickandmortyapp.data.dtos.characters_dtos

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Origin(
    @SerializedName("name") val name: String,
    @SerializedName("url")val originUrl: String
)