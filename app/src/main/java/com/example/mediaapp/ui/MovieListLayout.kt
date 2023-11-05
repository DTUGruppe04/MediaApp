package com.example.mediaapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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

data class Movie(
    val title: String,
    val genre: String,
    val description: String,
    val poster: Painter
)

class MovieListLayout(private val movies: List<Movie>) {

    private val defaultTextStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight(400),
        color = Color(0xFFCAC4D0),
        letterSpacing = 0.25.sp
    )

    @Composable
    fun MovieList() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF141218))
        ) {
            items(movies) { movie ->
                MovieListItem(movie)
                Divider(
                    color = Color.Gray,
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }

    @Composable
    fun MovieListItem(movie: Movie) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = movie.poster,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .composeImageModifier()
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(text = movie.title, style = defaultTextStyle)
                Text(text = movie.genre, style = defaultTextStyle)
                Text(text = movie.description, style = defaultTextStyle)
            }
        }
    }

    private fun Modifier.composeImageModifier(): Modifier {
        return this
            .shadow(elevation = 4.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(10.dp))
            .padding(0.5.dp)
            .width(96.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(142.dp)
    }
}
