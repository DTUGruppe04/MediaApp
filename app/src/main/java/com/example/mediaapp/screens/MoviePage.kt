package com.example.mediaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkAdd
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mediaapp.R
import com.example.mediaapp.apirequests.APIHandler
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
    val genreIds = movieDetails?.genres

    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
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
                MovieDescription(movie)
            }
            /* TODO: Add director and actors
            item {
                Detail(detail = movie., infoList = listOf(movie.director))
            }
            item {
                Detail(detail = "Actors", infoList = movie.actors)
            }*/
        }
    }
}

//060404
@Composable
fun MovieDescription(movie: TMDBMovieDetail) {
    val genreGenre = movie.genres
    val genreIds = convertGenresToIntList(genreGenre)
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color(0xFF3F3F3F))
        .padding(5.dp)
        .fillMaxHeight()) {

        AsyncImage(
            model = baseURL + (movie.poster_path ?: failURL),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.composeImageModifier())
        Text(text = movie.title,
            modifier = Modifier
                .padding(start = 130.dp, bottom = 130.dp),
            color = Color.White,
            fontSize = 30.sp)
        Row(modifier = Modifier
            .padding(start = 130.dp)
            .padding(top = 60.dp)
            .height(20.dp)
            .fillMaxWidth()) {
            GenreTags(genreIds)
        }


        Box(modifier = Modifier
            .padding(start = 130.dp, top = 90.dp)
            .fillMaxHeight()
            .fillMaxWidth()) {
            ExpandableText(movie.overview)
        }
        Row(modifier = Modifier
            .padding(start = 130.dp)
            .padding(top = 180.dp)
            .fillMaxWidth()
            .height(40.dp)) {
            Box(modifier = Modifier
                .fillMaxHeight()
                .width(60.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color(0xff4a4458))
            ) {
                Text(text = "Rate this",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Box(modifier = Modifier
                .fillMaxHeight()) {
                Icon(imageVector = Icons.Filled.Star,
                    contentDescription = "star",
                    tint = Color.Yellow,
                    modifier = Modifier
                        .fillMaxHeight())
            }
            Box(modifier = Modifier
                .padding(10.dp)
                .fillMaxHeight()) {
                Text(text = movie.vote_count.toString(),
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxHeight())
            }
            Box(modifier = Modifier
                .padding(start = 15.dp)
                .fillMaxHeight()) {
                Icon(imageVector = Icons.Outlined.BookmarkAdd,
                    contentDescription = "Bookmark",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp))
            }
        }
        Box(modifier = Modifier
            .padding(top = 160.dp)
        ) {
            Text(text = movie.release_date, color = Color.White)
        }
    }
}
@Composable
private fun Modifier.composeImageModifier(): Modifier {
    return this
        .shadow(elevation = 4.dp, spotColor = MaterialTheme.colorScheme.background)
        .border(1.dp, Color(0xFF000000), RoundedCornerShape(10.dp))
        .height(150.dp)
        .clip(RoundedCornerShape(10.dp))
}
//can be made more customizable by making the textlength decide the length of the box
@Composable
fun GenreTags(genreIds: List<Int>) {
    val genres = APIHandler().getGenrebyID(genreIds)
    Row(modifier = Modifier.
    horizontalScroll(rememberScrollState())) {
        for (genre in genres) {
            TagBox(shape = RoundedCornerShape(4.dp), tag = genre)
            Spacer(modifier = Modifier.width(8.dp)) // Add space between tags
        }
    }
}
fun convertGenresToIntList(genres: List<Genre>): List<Int> {
    return genres.map { it.id }
}
@Composable
fun ExpandableText(text: String, maxLength: Int = 150) {
    var isExpanded by remember { mutableStateOf(false) }

    val displayText = if (isExpanded || text.length <= maxLength) {
        text
    } else {
        text.take(maxLength) + "..."
    }

    Column {
        Text(
            text = displayText,
            color = Color.White,
            fontSize = 14.sp,
            lineHeight = 14.sp,
            modifier = Modifier.clickable { isExpanded = !isExpanded }
        )

        if (text.length > maxLength && !isExpanded) {
            Text(
                text = "View More",
                color = Color.White,
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
        .padding(top = 20.dp)
        .padding(start = 20.dp)) {
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
