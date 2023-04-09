package com.example.rickandmortyapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters_results_remote_keys_table")
data class CharactersResultsRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prev: Int?,
    val next: Int?
)