package com.example.mediaapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mediaapp.Screen
import com.example.mediaapp.models.TMDBMovie

object SearchQueryLayout {
    private const val baseURL = "https://image.tmdb.org/t/p/original"
    private const val failURL = "https://img.freepik.com/premium-vector/default-image-icon-vector-missing-picture-page-website-design-mobile-app-no-photo-available_87543-11093.jpg"

    @Composable
    fun SearchQueryList(movies: List<TMDBMovie>, navController: NavController) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            items(movies) { movie ->
                SearchQueryListItem(
                    movie = movie,
                    movieId = movie.id,
                    navController = navController
                )
                Divider(
                    color = MaterialTheme.colorScheme.outline,
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(start = 2.dp, end = 2.dp)
                )
            }
        }
    }
    @Composable
    fun ShortenedText(text: String): String {
        var shortText: String = text
        // Check if the text needs truncation
        if (text.length > 80) {
            shortText = text.take(80) + "..."
        }
        return shortText
    }
    @Composable
    fun SearchQueryListItem(movie: TMDBMovie,movieId: Int, navController: NavController) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 24.dp, top = 8.dp, bottom = 8.dp)
                .clickable {
                    navController.navigate("${Screen.MoviePage.route}/$movieId")
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = baseURL + (movie.poster_path ?: failURL),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.composeImageModifier()
            )
            Column (
                modifier = Modifier.padding(start = 16.dp)
            ){
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = ShortenedText(text = movie.overview),
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 8.dp))
            }
        }
    }

    @Composable
    private fun Modifier.composeImageModifier(): Modifier {
        return this
            .shadow(elevation = 4.dp, spotColor = MaterialTheme.colorScheme.background)
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(10.dp))
            .padding(0.5.dp)
            .width(96.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(142.dp)
    }
}