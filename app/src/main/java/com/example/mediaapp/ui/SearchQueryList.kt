package com.example.mediaapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediaapp.Screen

class SearchQueryList {
    @Composable
    fun SearchQueryList(){
    }
    @Composable
    fun SearchQueryList(title: String, genre: String, description: String, poster: Painter, navController: NavController) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable {
                    navController.navigate(Screen.MoviePage.route)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = poster,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .shadow(elevation = 4.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
                    .border(width = 1.dp, color = Color(0xFF000000), shape = RoundedCornerShape(size = 10.dp))
                    .padding(0.5.dp)
                    .width(96.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .height(142.dp),
            )
            Column (
                modifier = Modifier.padding(start = 16.dp)
            ){
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFCAC4D0),

                        letterSpacing = 0.25.sp,
                    )
                )
                Text(
                    text = genre,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFCAC4D0),
                        letterSpacing = 0.25.sp,
                    )
                )
                Text(
                    text = description,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFCAC4D0),

                        letterSpacing = 0.25.sp,
                    )
                )
            }
        }
    }
}