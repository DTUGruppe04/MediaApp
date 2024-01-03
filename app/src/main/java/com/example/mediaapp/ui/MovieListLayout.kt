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
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.mediaapp.models.Genre
import com.example.mediaapp.models.WatchlistMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MovieListLayout(private val movies: List<WatchlistMovie>, private val navController: NavController, private val scope: CoroutineScope) {
    private val baseURL = "https://image.tmdb.org/t/p/original"
    @Composable
    fun MovieList() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            items(movies) { movie ->
                MovieListItem(movie, navController, scope)
                Divider(
                    color = MaterialTheme.colorScheme.outline,
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }

    @Composable
    fun MovieListItem(watchlistMovie: WatchlistMovie, navController: NavController, scope: CoroutineScope) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable {
                    scope.launch {
                        navController.navigate("${Screen.MoviePage.route}/${watchlistMovie.movieID}")
                    }
                },
            verticalAlignment = Alignment.CenterVertically

        ) {
            AsyncImage(
                model = baseURL + watchlistMovie.posterPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .border(
                        1.dp,
                        color = MaterialTheme.colorScheme.surface,
                        RoundedCornerShape(10.dp)
                    )
                    .padding(0.5.dp)
                    .width(96.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .height(142.dp)
                    .padding(start = 10.dp, top = 5.dp)
                    .width(90.dp)
                    .height(139.dp)
                    .clip(RoundedCornerShape(10.dp))

            )

            val genreList = mutableListOf<String>()

            val maxIterations = minOf(3, watchlistMovie.genres.size)
            for(i in 0 until maxIterations) {
                val tempGenre = watchlistMovie.genres[i] as? HashMap<*, *>
                val genreName = tempGenre?.get("name") as? String
                genreList.add(genreName ?: "")
            }

            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(text = watchlistMovie.title, style = MaterialTheme.typography.titleMedium)

                LazyRow() {
                    items(genreList) { genre ->
                        Text(
                            text = genre,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(top = 3.dp, bottom = 3.dp, end = 5.dp)
                        )
                    }
                }
            }
        }
    }

}

