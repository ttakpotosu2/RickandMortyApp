package com.example.rickandmortyapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("location_results_remote_keys_table")
data class LocationResultsRemoteKeys (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prev: Int?,
    val next: Int?
)