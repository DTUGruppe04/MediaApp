package com.example.mediaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.BookmarkRemove
import androidx.compose.material.icons.outlined.Recommend
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mediaapp.R
import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.Crew
import com.example.mediaapp.models.Genre
import com.example.mediaapp.models.TMDBMovieDetail
import com.example.mediaapp.ui.nav.TopNavBarD
import com.example.mediaapp.ui.theme.MediaAppTheme
import com.example.mediaapp.ui.theme.md_theme_dark_background
import com.example.mediaapp.viewModels.MovieDetailViewModel

private const val baseURL = "https://image.tmdb.org/t/p/original"
private const val failURL = "https://img.freepik.com/premium-vector/default-image-icon-vector-missing-picture-page-website-design-mobile-app-no-photo-available_87543-11093.jpg"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailPage(
    movieId: String,
    viewModel: MovieDetailViewModel = viewModel(),
    navController: NavController,
    drawerState: DrawerState) {
    val movieDetails by viewModel.movieDetails.collectAsState()
    val movieCredits by viewModel.movieCredits.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
        viewModel.fetchMovieCredits(movieId)
    }
    val movie = movieDetails ?: return
    MediaAppTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
                .background(md_theme_dark_background)
        ) {

            item {
                TopNavBarD(navController = navController, drawerState = drawerState)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    AsyncImage(
                        model = baseURL + (movie.backdrop_path ?: failURL),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .height(32.dp)
                                .width(132.dp)
                                .wrapContentWidth()
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(color = Color(0xFF1D1B20))
                        ) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.VisibilityOff,
                                        contentDescription = "N/A",
                                        tint = colorResource(R.color.white),
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .size(18.dp)
                                    )
                                    Text(
                                        text = "Not Watched",
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        modifier = Modifier
                                            .padding(start = 8.dp, end = 8.dp)
                                    )
                                }

                            }
                        }
                        Box(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .height(32.dp)
                                .width(126.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(color = Color(0xff4a4458))
                        ) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Icon(
                                        imageVector = Icons.Outlined.Recommend,
                                        contentDescription = "N/A",
                                        tint = colorResource(R.color.white),
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .size(18.dp)
                                    )
                                    Text(
                                        text = "Recommend",
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        modifier = Modifier
                                            .padding(start = 8.dp, end = 8.dp)
                                    )
                                }

                            }
                        }
                    }
                }
            }
            item {
                //Top part
                MovieDescription(movie, true, viewModel, movieId)
            }/* TODO Add Director and Actors
            item {
                Detail(detail = "Crew", infoList = convertCrewToStringList(movieCredits?.crew))
            }*/
            /*item {
                Detail(detail = "Actors", infoList = convertCrewToStringList(movieCredits?.crew))
            }*/
        }
    }
}



@Composable
fun MovieDescription(movie: TMDBMovieDetail, bookmarkStatus: Boolean, viewModel: MovieDetailViewModel, movieId: String) {
    val genreIds = convertGenresToIntList(movie.genres)
    var isBookmarked by remember { mutableStateOf(bookmarkStatus) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = baseURL + (movie.poster_path ?: failURL),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.composeImageModifier()
            )

            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = movie.title,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )
                GenreTags(genreIds)
                ExpandableText(movie.overview)

            }
        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // "Rate this" Box
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xff4a4458))
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Rate this",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                // Star Icon
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "star",
                    tint = Color.Yellow
                )

                // Vote Count
                Text(
                    text = movie.vote_count.toString(),
                    color = Color.White,
                    modifier = Modifier.padding(start = 5.dp, end = 15.dp),
                    fontSize = 14.sp
                )
            }
            // Bookmark Icon
            Icon(
                imageVector = if (isBookmarked) Icons.Outlined.BookmarkAdd else Icons.Outlined.BookmarkRemove,
                contentDescription = "Bookmark",
                tint = Color.White,
                modifier = Modifier.size(30.dp).clickable {
                    viewModel.addToWatchlist(movieId)
                    isBookmarked = !isBookmarked },
            )
        }
        Text(
            text = "Release Date: ${movie.release_date}",
            color = Color.White,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
private fun Modifier.composeImageModifier(): Modifier {
    return this
        .shadow(elevation = 4.dp, spotColor = MaterialTheme.colorScheme.background)
        .border(1.dp, Color(0xFF000000), RoundedCornerShape(10.dp))
        .height(180.dp)
        .clip(RoundedCornerShape(10.dp))
}
//can be made more customizable by making the textlength decide the length of the box
@Composable
fun GenreTags(genreIds: List<Int>) {
    val genres = APIHandler().getGenrebyID(genreIds)
    Row (modifier = Modifier.padding(top = 10.dp)){
        for (genre in genres) {
            TagBox(shape = RoundedCornerShape(4.dp), tag = genre)
            Spacer(modifier = Modifier.width(8.dp)) // Add space between tags
        }
    }
}

// Functions that convert the Api lists into the one you want
fun convertGenresToIntList(genres: List<Genre>): List<Int> {
    return genres.map { it.id }
}
fun convertCrewToStringList(crew: List<Crew>?): List<String> {
    return crew?.map { it.toString() } ?: emptyList()
}

@Composable
fun ExpandableText(text: String, maxLength: Int = 180) {
    var isExpanded by remember { mutableStateOf(false) }

    val displayText = if (isExpanded || text.length <= maxLength) {
        text
    } else {
        text.take(maxLength) + "..."
    }

    Column {
        Text(
            text = displayText,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable { isExpanded = !isExpanded },
        )

        if (text.length > maxLength && !isExpanded) {
            Text(
                text = stringResource(R.string.view_more),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                modifier = Modifier.clickable { isExpanded = true }
            )
        }
    }
}


@Composable
fun TagBox(shape : Shape, tag : String) {
    Column(modifier = Modifier
        .height(15.dp)
        .width(80.dp)) {
        Box(modifier = Modifier
            .fillMaxSize()
            .clip(shape)
            .background(color = Color(0xFF282727))) {
            Text(text = tag,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 12.sp)
        }
    }
}

@Composable
fun Detail(detail : String, infoList : List<String>) {
    Box(modifier = Modifier
        .padding(top = 10.dp)
        .padding(start = 10.dp)) {
        Text(text = detail,
            fontWeight = FontWeight.Bold,
            color = Color.White)
    }
    for (info in infoList) {
        Box(modifier = Modifier
            .padding(top = 5.dp)
            .padding(start = 20.dp)) {
            Text(text = info,
                color = Color.White)
        }
    }
}
