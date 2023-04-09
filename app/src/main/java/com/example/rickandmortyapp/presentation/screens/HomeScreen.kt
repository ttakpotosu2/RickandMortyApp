package com.example.rickandmortyapp.presentation.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
            .background(Color(0xFF121010)),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_launcher_background),
//            contentDescription = null,
//            modifier = Modifier
//
//                .clip(CircleShape).size(100.dp)
//                .padding(horizontal = 16.dp)
//        )
        Text(
            text = stringResource(id = R.string.home_page_text_one),
            style = TextStyle(
                fontSize = 70.sp,
                color = Color.White
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(text = stringResource(id = R.string.home_page_text_two),
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Gray
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                CategoryCard(
                    text = "x",
                    onClickAction = { navController.navigate(Screen.CharactersScreen.route)}
                )
            }
            item {
                CategoryCard(
                    text = "l",
                    onClickAction = { navController.navigate(Screen.LocationsScreen.route)}
                )
            }
            item {
                CategoryCard(
                    text = "e",
                    onClickAction = { navController.navigate(Screen.EpisodesScreen.route)}
                )
            }
        }
        Text(text = stringResource(id = R.string.home_page_text_three),
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Gray
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
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
            .size(160.dp)
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

//@Preview(
//    device = "spec:width=1440px,height=3120px,dpi=640", showBackground = true,
//    showSystemUi = true
//)
//@Composable
//fun Prev1() {
//    HomeScreen()
//}