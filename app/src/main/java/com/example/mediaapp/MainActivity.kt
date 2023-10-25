package com.example.mediaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.mediaapp.ui.theme.MediaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaAppTheme {
                // A surface container using the 'background' color from the theme
                ProfilePageLayout()
                }
            }
        }

    }

@Preview
@Composable
fun ProfilePageLayout() {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF2E2E2E))) {
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
                            StandardBoxInRow(R.drawable.thegodfather, R.string.thegodfather)
                        }
                        item {
                            StandardBoxInRow(R.drawable.parasite, R.string.parasite)
                        }
                        item {
                            StandardBoxInRow(R.drawable.thematrix, R.string.thematrix)
                        }
                        item {
                            StandardBoxInRow(R.drawable.casablanca, R.string.casablanca)
                        }
                        item {
                            StandardBoxInRow(R.drawable.indianajones, R.string.indianajones)
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
                            StandardBoxInRow(R.drawable.oppenheimer, R.string.oppenheimer)
                        }
                        item {
                            StandardBoxInRow(R.drawable.thedayaftertomorrow, R.string.thedayaftertomorrow)
                        }
                        item {
                            StandardBoxInRow(R.drawable.avatarthewayofwater, R.string.avatarthewayofwater)
                        }
                        item {
                            StandardBoxInRow(R.drawable.freeguy, R.string.freeguy)
                        }
                        item {
                            StandardBoxInRow(R.drawable.readyplayerone, R.string.readyplayerone)
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
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text (
                        stringResource(watched),
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 9.dp, top = 0.dp)
                            .height(28.dp)
                    )
                    Text (
                        stringResource(R.string.watched),
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
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text (
                        stringResource(reviews),
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 9.dp, top = 0.dp)
                            .height(28.dp)
                    )
                    Text (
                        stringResource(R.string.reviews),
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
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text (
                        stringResource(rated),
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 9.dp, top = 0.dp)
                            .height(28.dp)
                    )
                    Text (
                        stringResource(R.string.rated),
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
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text (
                        stringResource(recommends),
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 9.dp, top = 0.dp)
                            .height(28.dp)
                    )
                    Text (
                        stringResource(R.string.recommends),
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
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text (
                        stringResource(saved),
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 9.dp, top = 0.dp)
                            .height(28.dp)
                    )
                    Text (
                        stringResource(R.string.saved),
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
        }
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
            Column(modifier = Modifier.size(130.dp, 160.dp)) {
                Image(
                    painter = painterResource(profilePicture),
                    contentDescription = "profile_picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 9.dp, top = 7.dp, end = 0.dp, bottom = 3.dp)
                        .width(118.dp)
                        .height(119.dp)
                        .clip(RoundedCornerShape(25.dp))
                )
                Row {
                    Text (
                        stringResource(followers),
                        fontSize = 10.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 9.dp, top = 0.dp)
                            .height(28.dp)
                    )
                    Text (
                        stringResource(R.string.followers),
                        fontSize = 10.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        color = colorResource(id = R.color.follow_text_color),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 3.dp, top = 0.dp)
                            .height(28.dp)
                    )
                    Text (
                        stringResource(following),
                        fontSize = 10.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 10.dp, top = 0.dp)
                            .height(28.dp)
                    )
                    Text (
                        stringResource(R.string.follows),
                        fontSize = 10.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(400),
                        color = colorResource(id = R.color.follow_text_color),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 3.dp, top = 0.dp)
                            .height(28.dp)
                    )
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
                Row(modifier = Modifier.size(105.dp, 13.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text (
                        stringResource(nameOfUser),
                        color = Color.White,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(start = 10.dp, top = 0.dp, end = 8.dp)
                    )
                    Image(modifier = Modifier
                        .size(13.dp, 8.dp)
                        .padding(start = 0.dp, top = 0.dp), painter = painterResource(id = countryFlag),
                        contentDescription = "country_flag")
                    Text (
                        stringResource(countryName),
                        color = Color.White,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(start = 2.dp, top = 0.dp)
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