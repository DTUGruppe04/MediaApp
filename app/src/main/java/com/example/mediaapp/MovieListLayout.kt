package com.example.mediaapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediaapp.ui.theme.MediaAppTheme

class MovieListLayout(private val movies: List<Movie>) {

    data class Movie(
        val title: String,
        val genre: String,
        val description: String,
        val poster: Painter
    )
    @Composable
    fun MovieList(){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF141218)),
        ) {
            items(movies) { movie ->
                MovieListItem(
                    title = movie.title,
                    genre = movie.genre,
                    description = movie.description,
                    poster = movie.poster
                )
                Divider(
                    color = Color.Gray,
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
            }
        }
    }

    @Composable
    fun MovieListItem(title: String, genre: String, description: String, poster: Painter) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
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
@Preview(showBackground = true)
@Composable
fun PreviewMovieListLayout() {
    MediaAppTheme {
        FollowingListPage()
    }
}

