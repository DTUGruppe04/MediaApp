package com.example.mediaapp.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediaapp.R
import com.example.mediaapp.Screen
import com.example.mediaapp.ui.theme.MediaAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainPageLayout(navController: NavController, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    val mainPageTopPicture = listOf(
        R.drawable.barbie,
        R.drawable.oppenheimer
    )
    val mainPageTopString1 = listOf(
        R.string.main_page_text1_1,
        R.string.main_page_text1_2
    )
    val mainPageTopString2 = listOf(
        R.string.main_page_text2_1,
        R.string.main_page_text2_2
    )
    val mainPageTopPoster = listOf(
        R.drawable.barbie_icon,
        R.drawable.oppenheimerposter
    )
    MediaAppTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp)
        ) {
            item {
                //This is the uppermost part of the main page
                val pagerState = rememberPagerState(pageCount = {mainPageTopPicture.size})
                Box(modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth()
                ) {
                    HorizontalPager(
                        state = pagerState,
                        key = { mainPageTopPicture[it] },
                        pageSize = PageSize.Fill
                    ) { index ->
                        Box {
                            Column {
                                Image(
                                    painter = painterResource(id = mainPageTopPicture[index]),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(220.dp)
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column {
                                        Text(
                                            stringResource(id = mainPageTopString1[index]),
                                            color = MaterialTheme.colorScheme.onSurface,
                                            style = MaterialTheme.typography.titleMedium,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(5.dp)
                                        )
                                        Text(
                                            stringResource(id = mainPageTopString2[index]),
                                            color = MaterialTheme.colorScheme.onSurface,
                                            style = MaterialTheme.typography.labelMedium,
                                            fontSize = 10.sp,
                                            modifier = Modifier
                                                .padding(start = 5.dp, bottom = 5.dp)
                                        )
                                    }
                                }
                            }
                            Image(
                                painter = painterResource(id = mainPageTopPoster[index]),
                                contentDescription = "poster",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .offset(x = 33.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .height(121.dp)
                                    .width(72.dp)
                                    .align(Alignment.BottomStart)
                            )
                        }
                    }
                }
            }
            item {
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
                            item {
                                StandardBoxInRow(
                                    navController,
                                    R.drawable.first_movie,
                                    R.string.main_page_first_movie
                                )
                            }
                            item {
                                StandardBoxInRow(
                                    navController,
                                    R.drawable.second_movie,
                                    R.string.main_page_second_movie
                                )
                            }
                            item {
                                StandardBoxInRow(
                                    navController,
                                    R.drawable.third_movie,
                                    R.string.main_page_third_movie
                                )
                            }
                            item {
                                StandardBoxInRow(
                                    navController,
                                    R.drawable.fourth_movie,
                                    R.string.main_page_fourth_movie
                                )
                            }
                            item {
                                StandardBoxInRow(
                                    navController,
                                    R.drawable.fifth_movie,
                                    R.string.main_page_fourth_movie
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
                                StandardBoxInRow(
                                    navController,
                                    R.drawable.second_row_first_movie,
                                    R.string.main_page_first_row_first_movie
                                )
                            }
                            item {
                                StandardBoxInRow(
                                    navController,
                                    R.drawable.second_row_second_movie,
                                    R.string.main_page_first_row_second_movie
                                )
                            }
                            item {
                                StandardBoxInRow(
                                    navController,
                                    R.drawable.second_row_third_movie,
                                    R.string.main_page_first_row_third_movie
                                )
                            }
                            item {
                                StandardBoxInRow(
                                    navController,
                                    R.drawable.second_row_fourth_movie,
                                    R.string.main_page_first_row_fourth_movie
                                )
                            }
                            item {
                                StandardBoxInRow(
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
                                StandardBoxInRow(
                                    navController,
                                    R.drawable.third_row_first_movie,
                                    R.string.main_page_third_row_first_movie
                                )
                            }
                            item {
                                StandardBoxInRow(
                                    navController,
                                    R.drawable.third_row_second_movie,
                                    R.string.main_page_third_row_second_movie
                                )
                            }
                            item {
                                StandardBoxInRow(
                                    navController,
                                    R.drawable.third_row_third_movie,
                                    R.string.main_page_third_row_third_movie
                                )
                            }
                            item {
                                StandardBoxInRow(
                                    navController,
                                    R.drawable.third_row_fourth_movie,
                                    R.string.main_page_third_row_fourth_movie
                                )
                            }
                            item {
                                StandardBoxInRow(
                                    navController,
                                    R.drawable.third_row_fifth_movie,
                                    R.string.main_page_third_row_fifth_movie
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StandardBoxInRow(navController: NavController, image: Int, string: Int) {
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