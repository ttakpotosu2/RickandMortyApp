package com.example.rickandmortyapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickandmortyapp.presentation.screens.*

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.CharactersScreen.route) {
            CharactersScreen(navController)
        }
        composable(route = Screen.CharacterDetailScreen.route + "/{characterId}") {
            CharacterDetailScreen(navHostController = navController)
        }
        composable(route = Screen.LocationsScreen.route) {
            LocationScreen(navigateUp = navController::navigateUp, openLocationDetailScreen = { id ->
                navController.navigate(Screen.LocationDetailScreen.route + "/${id}")
            })
        }
        composable(route = Screen.LocationDetailScreen.route + "/{locationId}") {
            LocationDetailScreen(navController) //from location detail screen to character detail screen
        }
        composable(route = Screen.EpisodesScreen.route) {
            EpisodesScreen(navigateUp = navController::navigateUp, openEpisodeDetailScreen = { id ->
                navController.navigate(Screen.EpisodeDetailScreen.route + "/${id}")
            })
        }
        composable(route = Screen.EpisodeDetailScreen.route + "/{episodeId}") {
            EpisodeDetailScreen(navController = navController) //from episode detail screen to character detail screen
        }
    }
}