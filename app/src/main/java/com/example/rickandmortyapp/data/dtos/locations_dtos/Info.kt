package com.example.rickandmortyapp.data.dtos.locations_dtos

data class Info(
    val count: Int,
    val next: String? = null,
    val pages: Int,
    val prev: String? = null
)