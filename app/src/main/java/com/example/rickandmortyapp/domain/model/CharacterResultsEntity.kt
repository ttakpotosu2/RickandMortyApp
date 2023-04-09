package com.example.rickandmortyapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmortyapp.data.dtos.characters_dtos.Origin

@Entity(tableName = "characters_results_table")
data class CharacterResultsEntity(
    @PrimaryKey val id: Int,
    val episodes: List<String>,
    val gender: String,
    val image: String,
    val charactersName: String,
    val charactersUrl: String,
    val status: String,
    val origin: Origin,
    val species: String
)