package com.example.rickandmortyapp.presentation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.presentation.navigation.Screen

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121010))
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.home_page_text_one),
            style = TextStyle(
                fontSize = 70.sp,
                color = Color.White
            )
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CategoryCard(
                text = "x",
                onClickAction = { navController.navigate(Screen.CharactersScreen.route)}
            )
            CategoryCard(
                text = "l",
                onClickAction = { navController.navigate(Screen.LocationsScreen.route)}
            )
            CategoryCard(
                text = "e",
                onClickAction = { navController.navigate(Screen.EpisodesScreen.route)}
            )
        }
    }
}

@Composable
fun CategoryCard(
    text: String,
    onClickAction: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF3D3C3C))
            .border(width = 1.dp, color = Color.White, shape = RoundedCornerShape(12.dp))
            .clickable(onClick = onClickAction)
    ) {
        Text(
            text = text.uppercase(),
            style = TextStyle(
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}