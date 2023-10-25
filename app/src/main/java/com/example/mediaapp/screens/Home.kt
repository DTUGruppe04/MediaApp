package com.example.mediaapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediaapp.R

@Composable
fun MainPageLayout() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)
    ){
        item {
            //This is the uppermost part of the main page
            Box() {
                Column(modifier = Modifier
                    .fillMaxWidth()) {
                    Image(painter = painterResource(R.drawable.barbie),
                        contentDescription = "barbie",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop)
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(colorResource(R.color.box_separation_color))) {
                        Row {
                            Column {
                                Text(
                                    stringResource(R.string.main_page_text1),
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(start = 120.dp, top = 5.dp)
                                )
                                Text(
                                    stringResource(R.string.main_page_text2),
                                    color = Color.White,
                                    fontSize = 10.sp,
                                    modifier = Modifier.padding(start = 120.dp)
                                )
                            }
                            Spacer(Modifier.size(75.dp))
                            Image(painter = painterResource(R.drawable.bookmark),
                                contentDescription = "bookmark",
                                modifier = Modifier
                                    .padding(5.dp)
                                    .size(33.dp))
                        }
                    }
                }
                Image(painter = painterResource(R.drawable.barbie_icon),
                    contentDescription = "barbie_icon",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 32.dp, top = 152.dp)
                        .height(121.dp)
                        .width(72.dp)
                        .clip(RoundedCornerShape(10.dp)))
                Image(
                    painter = painterResource(R.drawable.settings_icon),
                    contentDescription = "settings",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(24.dp)
                )
                Image(
                    painter = painterResource(R.drawable.arrow_left),
                    contentDescription = "arrow_left",
                    modifier = Modifier
                        .padding(start = 10.dp, top = 210.dp)
                        .size(16.dp)
                )
                Image(
                    painter = painterResource(R.drawable.arrow_right),
                    contentDescription = "arrow_right",
                    modifier = Modifier
                        .padding(start = 365.dp, top = 210.dp)
                        .size(16.dp)
                )
            }
        }
        item {
            //This is for the first horizontal list
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(colorResource(R.color.box_row_color))) {
                Column {
                    Text(
                        stringResource(R.string.main_page_trending),
                        color = Color.White,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                    )
                    LazyRow(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            StandardBoxInRow(R.drawable.first_movie, R.string.main_page_first_movie)
                        }
                        item {
                            StandardBoxInRow(R.drawable.second_movie, R.string.main_page_second_movie)
                        }
                        item {
                            StandardBoxInRow(R.drawable.third_movie, R.string.main_page_third_movie)
                        }
                        item {
                            StandardBoxInRow(R.drawable.fourth_movie, R.string.main_page_fourth_movie)
                        }
                        item {
                            StandardBoxInRow(R.drawable.fifth_movie, R.string.main_page_fourth_movie)
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
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(colorResource(R.color.box_row_color))) {
                Column {
                    Text(
                        stringResource(R.string.main_page_top_picks),
                        color = Color.White,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                    )
                    LazyRow(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            StandardBoxInRow(R.drawable.second_row_first_movie, R.string.main_page_first_row_first_movie)
                        }
                        item {
                            StandardBoxInRow(R.drawable.second_row_second_movie, R.string.main_page_first_row_second_movie)
                        }
                        item {
                            StandardBoxInRow(R.drawable.second_row_third_movie, R.string.main_page_first_row_third_movie)
                        }
                        item {
                            StandardBoxInRow(R.drawable.second_row_fourth_movie, R.string.main_page_first_row_fourth_movie)
                        }
                        item {
                            StandardBoxInRow(R.drawable.second_row_fifth_movie, R.string.main_page_first_row_fifth_movie)
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
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(colorResource(R.color.box_row_color))) {
                Column {
                    Text(
                        stringResource(R.string.main_page_friends_recommend),
                        color = Color.White,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                    )
                    LazyRow(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            StandardBoxInRow(R.drawable.third_row_first_movie, R.string.main_page_third_row_first_movie)
                        }
                        item {
                            StandardBoxInRow(R.drawable.third_row_second_movie, R.string.main_page_third_row_second_movie)
                        }
                        item {
                            StandardBoxInRow(R.drawable.third_row_third_movie, R.string.main_page_third_row_third_movie)
                        }
                        item {
                            StandardBoxInRow(R.drawable.third_row_fourth_movie, R.string.main_page_third_row_fourth_movie)
                        }
                        item {
                            StandardBoxInRow(R.drawable.third_row_fifth_movie, R.string.main_page_third_row_fifth_movie)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StandardBoxInRow(image: Int, string: Int) {
    Box(modifier = Modifier
        .width(90.dp)
        .height(180.dp)) {
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
            )
            Text(
                stringResource(string),
                color = Color.White,
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
        .background(colorResource(R.color.box_separation_color)))
}

@Composable
fun HomeScreen(navController: NavController) {
    MainPageLayout()
}

@Preview(showBackground = false)
@Composable
fun MainPagePreview() {
    MainPageLayout()
}
