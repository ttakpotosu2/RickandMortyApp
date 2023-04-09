package com.example.rickandmortyapp.data

import com.example.rickandmortyapp.data.dtos.locations_dtos.Info


data class PagedData<T>(
    val info: Info,
    val results: List<T>
)
