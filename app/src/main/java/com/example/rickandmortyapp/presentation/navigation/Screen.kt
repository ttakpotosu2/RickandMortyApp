package com.example.rickandmortyapp.presentation.navigation

sealed class Screen(val route: String){

    object Home: Screen("home_screen")
    object CharactersScreen: Screen("characters_screen")
    object CharacterDetailScreen: Screen("character_detail_screen")
    object LocationsScreen: Screen("locations_screen")
    object LocationDetailScreen: Screen("location_detail_screen")
    object EpisodesScreen: Screen("episodes_screen")
    object EpisodeDetailScreen: Screen("episode_detail_screen")
}
