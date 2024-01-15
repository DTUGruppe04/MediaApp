package com.example.mediaapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mediaapp.Screen
import com.example.mediaapp.models.WatchlistMovie
import com.example.mediaapp.viewModels.WatchlistViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MovieListLayout(
    private val movies: List<WatchlistMovie>,
    private val navController: NavController,
    private val scope: CoroutineScope,
    private val viewmodel: WatchlistViewModel
) {
    private val baseURL = "https://image.tmdb.org/t/p/original"
    
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun MovieList() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .combinedClickable(
                    onClick = {
                        if (viewmodel.deleteview.value) {
                            viewmodel.openDeleteView()
                        }
                    },
                    onLongClick = {
                        viewmodel.openDeleteView()
                    }
                )
        ) {
            items(movies) { movie ->
                MovieListItem(movie, navController, scope, viewmodel)
                Divider(
                    color = MaterialTheme.colorScheme.outline,
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
    @Composable
    fun MovieListItem(watchlistMovie: WatchlistMovie, navController: NavController, scope: CoroutineScope, viewmodel: WatchlistViewModel) {
        val deleteView = viewmodel.deleteview.collectAsState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .combinedClickable(
                    onClick = {
                        if (viewmodel.deleteview.value) {
                            viewmodel.openDeleteView()
                        } else {
                            scope.launch {
                                navController.navigate("${Screen.MoviePage.route}/${watchlistMovie.movieID}")
                            }
                        }

                    },
                    onLongClick = {
                        viewmodel.openDeleteView()
                    }
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(0.85f)
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

                    FlowRow(
                        maxItemsInEachRow = 3
                    ) {
                        genreList.forEach { genre ->
                            Text(
                                text = genre,
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(top = 3.dp, bottom = 3.dp, end = 5.dp)
                            )
                        }
                    }
                }
            }

            if (deleteView.value) {
                Icon(
                    imageVector = Icons.Filled.DeleteForever,
                    contentDescription = "deleteForever",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(34.dp)
                        .clickable {
                            viewmodel.removeMovieFromWatchlist(watchlistMovie.movieID)
                        }
                )
            }
        }
    }
}
@Composable
fun StandardBoxInRow(navController: NavController, movie_poster_path: String, movieTitle: String, scope: CoroutineScope, movieId: String) {
    Box(
        modifier = Modifier
            .width(90.dp)
            .height(180.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = movie_poster_path,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp)
                    .width(90.dp)
                    .height(139.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        scope.launch {
                            navController.navigate("${Screen.MoviePage.route}/$movieId")
                        }
                    }
            )
            Text(
                text = movieTitle,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 12.sp,
                softWrap = true,
                maxLines = 2,
                lineHeight = 1.em,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp)
                    .clickable {
                        scope.launch {
                            navController.navigate("${Screen.MoviePage.route}/$movieId")
                        }
                    }
            )
        }
    }
}

@Composable
fun StandardBoxInRowActors(actorPicPath: String, actorName: String, actorCharacter: String) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(200.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = actorPicPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp)
                    .width(90.dp)
                    .height(139.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Text(
                text = actorName,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 11.sp,
                softWrap = true,
                maxLines = 1,
                lineHeight = 1.em,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp)
            )
            Text(
                text = actorCharacter,
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 10.sp,
                softWrap = true,
                maxLines = 2,
                lineHeight = 1.em,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}


@Composable
fun StandardBoxInRowCrew(crewPicPath: String, crewName: String, crewJob: String) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(200.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = crewPicPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp)
                    .width(90.dp)
                    .height(139.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Text(
                text = crewName,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 11.sp,
                softWrap = true,
                maxLines = 1,
                lineHeight = 1.em,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp)
            )
            Text(
                text = crewJob,
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 10.sp,
                softWrap = true,
                maxLines = 2,
                lineHeight = 1.em,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

