package com.example.mediaapp.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediaapp.R
import com.example.mediaapp.ui.nav.TopNavBarC

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePageLayout(navController: NavController, drawerState: DrawerState) {
    Column {
        TopNavBarC(navController = navController, drawerState = drawerState)
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 75.dp)
            .background(color = Color(0xFF2E2E2E)))
        {
            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)) {
                    ProfileDescription(
                        profilePicture = R.drawable.profilepicture,
                        description = R.string.description,
                        countryFlag = R.drawable.dk,
                        countryName = R.string.country,
                        followers = R.string.followers_number,
                        following = R.string.follows_number,
                        username = R.string.username,
                        nameOfUser = R.string.name_of_user
                    )
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                        .background(color = Color(0xFF3F3F3F))
                ) {
                    Column {
                        Text(
                            stringResource(R.string.profile_page_favorite_movies),
                            color = Color.White,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                        )
                        LazyRow(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            item {
                                StandardBoxInRow(navController, R.drawable.thegodfather, R.string.thegodfather)
                            }
                            item {
                                StandardBoxInRow(navController, R.drawable.parasite, R.string.parasite)
                            }
                            item {
                                StandardBoxInRow(navController, R.drawable.thematrix, R.string.thematrix)
                            }
                            item {
                                StandardBoxInRow(navController, R.drawable.casablanca, R.string.casablanca)
                            }
                            item {
                                StandardBoxInRow(navController, R.drawable.indianajones, R.string.indianajones)
                            }
                        }
                    }
                }
            }
            item {
                Box(modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)) {
                    ProfileStatistics(
                        R.string.watched_number,
                        R.string.reviews_number,
                        R.string.rated_number,
                        R.string.recommends_number,
                        R.string.saved_number
                    ) }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                        .background(color = Color(0xFF3F3F3F))
                ) {
                    Column {
                        Text(
                            stringResource(R.string.profile_page_recently),
                            color = Color.White,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                        )
                        LazyRow(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            item {
                                StandardBoxInRow(navController, R.drawable.oppenheimer2, R.string.oppenheimer)
                            }
                            item {
                                StandardBoxInRow(navController, R.drawable.thedayaftertomorrow, R.string.thedayaftertomorrow)
                            }
                            item {
                                StandardBoxInRow(navController, R.drawable.avatarthewayofwater, R.string.avatarthewayofwater)
                            }
                            item {
                                StandardBoxInRow(navController, R.drawable.freeguy, R.string.freeguy)
                            }
                            item {
                                StandardBoxInRow(navController, R.drawable.readyplayerone, R.string.readyplayerone)
                            }
                        }
                    }
                }
            }
        }
    }
    }


@Composable
fun ProfileStatistics(
    watched: Int,
    reviews: Int,
    rated: Int,
    recommends: Int,
    saved: Int,
) {
    Box(modifier = Modifier
        .background(colorResource(id = R.color.background))
        .fillMaxWidth()
        .height(118.dp)) {
        Column(modifier = Modifier.padding(start = 7.dp, end = 7.dp)) {
            Text (
                stringResource(R.string.moviecollector),
                fontSize = 16.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 3.dp, top = 6.dp, bottom = 4.dp)
                    .height(28.dp)
            )
            Row(modifier = Modifier
                .height(66.dp)
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.lightgray))
                .padding(top = 9.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround){
                StatisticsText(watched, R.string.watched)
                StatisticsText(reviews, R.string.reviews)
                StatisticsText(rated, R.string.rated)
                StatisticsText(recommends, R.string.recommends)
                StatisticsText(saved, R.string.saved)
            }
        }
    }
}

@Composable
private fun StatisticsText(number: Int, text: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            stringResource(number),
            fontSize = 18.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 9.dp, top = 0.dp)
                .height(28.dp)
        )
        Text(
            stringResource(text),
            fontSize = 10.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 9.dp, top = 0.dp)
                .height(28.dp)
        )
    }
}

@Composable
fun ProfileDescription(
    profilePicture: Int,
    description: Int,
    countryFlag: Int,
    countryName: Int,
    followers: Int,
    following: Int,
    username: Int,
    nameOfUser: Int) {
    Box(modifier = Modifier
        .background(colorResource(id = R.color.background))
        .fillMaxWidth()
        .height(162.dp)) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.size(145.dp, 160.dp)) {
                Image(
                    painter = painterResource(profilePicture),
                    contentDescription = "profile_picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 9.dp, top = 7.dp, bottom = 3.dp)
                        .fillMaxWidth()
                        .height(119.dp)
                        .clip(RoundedCornerShape(25.dp))
                )
                Row {
                    FollowText(followers, paddingValue = 9.dp, color = colorResource(id = R.color.white))
                    FollowText(R.string.followers, paddingValue = 3.dp, color = colorResource(id = R.color.follow_text_color))
                    FollowText(following, paddingValue = 10.dp, color = colorResource(id = R.color.white))
                    FollowText(R.string.follows, paddingValue = 3.dp, color = colorResource(id = R.color.follow_text_color))
                }
            }
            Column {
                Text (
                    stringResource(username),
                    style = TextStyle(
                        fontSize = 22.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .width(105.dp)
                        .height(32.dp)
                )
                Row(modifier = Modifier.size(115.dp, 13.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Text (
                        stringResource(nameOfUser),
                        color = Color.White,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(start = 10.dp, end = 8.dp)
                    )
                    Image(modifier = Modifier
                        .size(13.dp, 8.dp)
                        .padding(start = 0.dp, top = 0.dp), painter = painterResource(id = countryFlag),
                        contentDescription = "country_flag")
                    Text (
                        stringResource(countryName),
                        color = Color.White,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
                Text (
                    stringResource(description),
                    color = Color.White,
                    fontSize = 10.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 5.dp)
                        .width(200.dp)
                )
            }
        }
    }
}

@Composable
private fun FollowText(text: Int, paddingValue: Dp, color: Color) {
    Text(
        stringResource(text),
        fontSize = 10.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight(400),
        color = color,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(start = paddingValue)
            .height(28.dp)
    )
}