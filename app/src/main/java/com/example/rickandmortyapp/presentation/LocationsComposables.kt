package com.example.rickandmortyapp.presentation

import android.text.TextUtils.replace
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortyapp.domain.model.LocationResultEntity

@Composable
fun LocationCard(
    location: LocationResultEntity,
    onItemClick: () -> Unit
) {
    Column(
       modifier = Modifier
           .fillMaxWidth()
           .height(80.dp)
           .background(Color.White.copy(.1f))
           .clickable { onItemClick() }
           .clip(RoundedCornerShape(12.dp))
           .border(shape = RoundedCornerShape(12.dp), width = 1.dp, color = Color.White)
           .padding(8.dp)
    ){
        Text(
            text = location.name,
            style = TextStyle(
                color = Color.White,
                fontSize = 38.sp,
            ),
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "${location.dimension} || ${location.name}",
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
            ),
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ResidentsItem(
    resident: String,
    onItemClick: () -> Unit
) {
    Box(modifier = Modifier
        .border(
            width = 1.dp,
            color = Color.White,
            shape = RoundedCornerShape(100.dp)
        )
        .padding(10.dp)
        .clickable { onItemClick() }
    ){
        Text(
            text = resident,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}