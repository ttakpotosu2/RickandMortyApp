package com.example.rickandmortyapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("episodes_results_table")
data class EpisodesResultsEntity(
    @SerializedName("air_date")
    val airDate: String,
//    val characters: List<String>,
    val created: String,
    val episode: String,
    @PrimaryKey val id: Int,
    val name: String,
    val url: String
)
