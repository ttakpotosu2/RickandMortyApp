package com.example.rickandmortyapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("locations_result_table")
data class LocationResultEntity(
    val created: String,
    val dimension: String,
    @PrimaryKey val id: Int,
    val name: String,
    val residents: List<Int>,// Should this be an Int?
    val type: String,
    val url: String
)