package com.example.mediaapp.screens

import PagerViewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mediaapp.R
import com.example.mediaapp.Screen
import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.ui.theme.MediaAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainPageLayout(viewModel: PagerViewModel = viewModel(), navController: NavController, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    val popularMovies by viewModel.popularMovies.collectAsState()
    val firstFiveMovies = popularMovies.take(5)
    val remainingMovies = popularMovies.drop(5)
    val baseURL = "https://image.tmdb.org/t/p/original"
    MediaAppTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp)
        ) {
            item {
                //This is the uppermost part of the main page
                val pageCount = firstFiveMovies.size
                val pagerState = rememberPagerState(pageCount = {popularMovies.size})


                LaunchedEffect("week") {
                    viewModel.fetchPopularMovies()
                }

                //The sliding horizontal pager
                Box(modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth()
                ) {
                    HorizontalPager(
                        state = pagerState,
                        key = {it},
                        pageSize = PageSize.Fill
                    ) { index ->
                        val movieId = popularMovies[index].id.toString()
                        val movie = firstFiveMovies.getOrNull(index)
                        movie?.let { safeMovie ->
                        Box(
                            modifier = Modifier
                                .height(280.dp)
                                .fillMaxWidth()
                        ) {
                            /* Have some problems with sometimes skipping two pages
                        LaunchedEffect("autoscroll") {
                            while(pageCount > 0) {
                                delay(8000)
                                scope.launch {
                                    val nextPage = (pagerState.currentPage + 1) % pageCount
                                    pagerState.animateScrollToPage(nextPage)
                                }
                            }
                        }

                         */
                            AsyncImage(
                                model = baseURL + movie.backdrop_path,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp)
                                    .clickable {
                                        scope.launch {
                                            navController.navigate("${Screen.MoviePage.route}/$movieId")
                                        }
                                    }
                                )
                                Row(
                                    verticalAlignment = Alignment.Bottom,
                                    horizontalArrangement = Arrangement.Start,
                                    modifier = Modifier
                                        .padding(bottom = 18.dp, end = 52.dp)
                                        .fillMaxWidth()
                                        .align(Alignment.BottomStart)
                                ) {
                                    AsyncImage(
                                        model = baseURL + movie.poster_path,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .padding(start = 52.dp, bottom = 5.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .height(121.dp)
                                            .width(72.dp)
                                            .clickable {
                                                scope.launch {
                                                    navController.navigate("${Screen.MoviePage.route}/$movieId")
                                                }
                                            }
                                    )
                                    Column(
                                        verticalArrangement = Arrangement.Bottom
                                    ) {
                                        Text(
                                            text = popularMovies[index].title,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            style = MaterialTheme.typography.titleMedium,
                                            textAlign = TextAlign.Center,
                                            softWrap = true,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier
                                                .padding(start = 10.dp)
                                                .clickable {
                                                    scope.launch {
                                                        navController.navigate("${Screen.MoviePage.route}/$movieId")
                                                    }
                                                }
                                        )
                                        Text(
                                            text = "Genre: " + APIHandler().getGenrebyID(
                                                popularMovies[index].genre_ids
                                            ),
                                            color = MaterialTheme.colorScheme.onSurface,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontSize = 10.sp,
                                            softWrap = true,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier
                                                .padding(start = 10.dp, bottom = 5.dp)
                                                .clickable {
                                                    scope.launch {
                                                        navController.navigate("${Screen.MoviePage.route}/$movieId")
                                                    }
                                                }
                                        )
                                    }
                                }
                            }
                        }
                    }
                    //The two arrows in the top
                    Box(
                        modifier = Modifier
                            .offset(y = -(65).dp)
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                    ) {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (pageCount > 1) {
                                        pagerState.animateScrollToPage(
                                            (pagerState.currentPage - 1 + pageCount) % pageCount
                                        )
                                    }
                                }
                            },
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "Go back",
                                modifier = Modifier
                                    .size(32.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        (pagerState.currentPage + 1) % pageCount
                                    )
                                }
                            },
                            modifier = Modifier.align(Alignment.CenterEnd)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "Go forward",
                                modifier = Modifier
                                    .size(32.dp)
                            )
                        }
                    }
                    //Menu-button in the top
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "menu",
                            tint = colorResource(R.color.top_navbar_icon_color),
                            modifier = Modifier
                                .padding(10.dp)
                                .size(40.dp)
                        )
                    }
                    //Add to watchlist button
                    IconButton(onClick = {
                            /*TODO*/
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.bookmark),
                            contentDescription = "bookmark",
                            modifier = Modifier
                                .size(33.dp)
                                .offset(y = -(13).dp)
                                .padding(end = 5.dp)
                        )
                    }
                }
            }
            item {//TODO
                //This is for the first horizontal list
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column {
                        Text(
                            stringResource(R.string.main_page_trending),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                        )
                        LazyRow(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(remainingMovies.size) { index ->
                                val movie = remainingMovies[index]
                                val movieId = remainingMovies[index].id.toString()

                                StandardBoxInRow(
                                    navController = navController,
                                    baseURL + movie.poster_path,
                                    movie.title,
                                    scope,
                                    movieId
                                )
                            }
                        }
                    }
                }
            }
            /*
            item {
                SeparationBox()
            }
            item {
                //This is for the second horizontal list
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column {
                        Text(
                            stringResource(R.string.main_page_top_picks),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                        )
                        LazyRow(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            item {
                                StandardBoxInRowOld(
                                    navController,
                                    R.drawable.second_row_first_movie,
                                    R.string.main_page_first_row_first_movie
                                )
                            }
                            item {
                                StandardBoxInRowOld(
                                    navController,
                                    R.drawable.second_row_second_movie,
                                    R.string.main_page_first_row_second_movie
                                )
                            }
                            item {
                                StandardBoxInRowOld(
                                    navController,
                                    R.drawable.second_row_third_movie,
                                    R.string.main_page_first_row_third_movie
                                )
                            }
                            item {
                                StandardBoxInRowOld(
                                    navController,
                                    R.drawable.second_row_fourth_movie,
                                    R.string.main_page_first_row_fourth_movie
                                )
                            }
                            item {
                                StandardBoxInRowOld(
                                    navController,
                                    R.drawable.second_row_fifth_movie,
                                    R.string.main_page_first_row_fifth_movie
                                )
                            }
                        }
                    }
                }
            }
            item {
                SeparationBox()
            }
            item {
                //This is for the third horizontal list
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column {
                        Text(
                            stringResource(R.string.main_page_friends_recommend),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                        )
                        LazyRow(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            item {
                                StandardBoxInRowOld(
                                    navController,
                                    R.drawable.third_row_first_movie,
                                    R.string.main_page_third_row_first_movie
                                )
                            }
                            item {
                                StandardBoxInRowOld(
                                    navController,
                                    R.drawable.third_row_second_movie,
                                    R.string.main_page_third_row_second_movie
                                )
                            }
                            item {
                                StandardBoxInRowOld(
                                    navController,
                                    R.drawable.third_row_third_movie,
                                    R.string.main_page_third_row_third_movie
                                )
                            }
                            item {
                                StandardBoxInRowOld(
                                    navController,
                                    R.drawable.third_row_fourth_movie,
                                    R.string.main_page_third_row_fourth_movie
                                )
                            }
                            item {
                                StandardBoxInRowOld(
                                    navController,
                                    R.drawable.third_row_fifth_movie,
                                    R.string.main_page_third_row_fifth_movie
                                )
                            }
                        }
                    }
                }
            }
            */
        }
    }
}

@Composable
fun StandardBoxInRowOld(navController: NavController, image: Int, string: Int) {
    Box(modifier = Modifier
        .width(90.dp)
        .height(180.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(image),
                contentDescription = "first_movie",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp)
                    .width(90.dp)
                    .height(139.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        navController.navigate(Screen.MoviePage.route)
                    }
            )
            Text(
                stringResource(string),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp),
                softWrap = true,
                maxLines = 2,
                lineHeight = 1.em,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        }
    }
}
@Composable
fun StandardBoxInRow(navController: NavController, movie_poster_path: String, movieTitle: String,scope: CoroutineScope,movieId: String) {
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

//Just a fancy spacer with colour
@Composable
fun SeparationBox() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(8.dp)
        .background(MaterialTheme.colorScheme.surface)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, drawerState: DrawerState) {
    MainPageLayout(navController = navController, drawerState = drawerState)
}

/*
@Preview(showBackground = false)
@Composable
fun MainPagePreview() {
    MainPageLayout()
}
 */