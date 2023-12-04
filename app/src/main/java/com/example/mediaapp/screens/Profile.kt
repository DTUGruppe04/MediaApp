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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mediaapp.R
import com.example.mediaapp.ui.nav.TopNavBarC
import com.example.mediaapp.ui.theme.MediaAppTheme
import com.example.mediaapp.viewModels.currentUserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePageLayout(
    navController: NavController,
    drawerState: DrawerState,
    viewModel: currentUserViewModel = viewModel()) {

    val currentUser by viewModel.currentUser.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCurrentUser()
    }

    val user = currentUser ?: return
    MediaAppTheme {
        Column {
            TopNavBarC(navController = navController, drawerState = drawerState)
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 75.dp)
                .background(color = MaterialTheme.colorScheme.surface))
            {
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)) {
                            ProfileDescription(
                                profilePicture = R.drawable.profilepicture,
                                description = if(user.user.description != "") user.user.description else stringResource(R.string.profile_page_no_description),
                                countryFlag = R.drawable.dk,
                                countryName = if(user.user.location != "") user.user.location else stringResource(R.string.profile_page_unknown),
                                followers = user.user.followers.size,
                                following = user.user.following.size,
                                username = user.user.username,
                                nameOfUser = if(user.user.name != "") user.user.name else stringResource(R.string.profile_page_unknown)
                            )
                        }
                    }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column {
                            Text(
                                stringResource(R.string.profile_page_favorite_movies),
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                            )
                            LazyRow(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                item {
                                    StandardBoxInRowOld(navController, R.drawable.thegodfather, R.string.thegodfather)
                                }
                                item {
                                    StandardBoxInRowOld(navController, R.drawable.parasite, R.string.parasite)
                                }
                                item {
                                    StandardBoxInRowOld(navController, R.drawable.thematrix, R.string.thematrix)
                                }
                                item {
                                    StandardBoxInRowOld(navController, R.drawable.casablanca, R.string.casablanca)
                                }
                                item {
                                    StandardBoxInRowOld(navController, R.drawable.indianajones, R.string.indianajones)
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
                            .clip(RoundedCornerShape(10.dp))
                            .background(color = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column {
                            Text(
                                stringResource(R.string.profile_page_recently),
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                            )
                            LazyRow(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                item {
                                    StandardBoxInRowOld(navController, R.drawable.oppenheimerposter, R.string.oppenheimer)
                                }
                                item {
                                    StandardBoxInRowOld(navController, R.drawable.thedayaftertomorrow, R.string.thedayaftertomorrow)
                                }
                                item {
                                    StandardBoxInRowOld(navController, R.drawable.avatarthewayofwater, R.string.avatarthewayofwater)
                                }
                                item {
                                    StandardBoxInRowOld(navController, R.drawable.freeguy, R.string.freeguy)
                                }
                                item {
                                    StandardBoxInRowOld(navController, R.drawable.readyplayerone, R.string.readyplayerone)
                                }
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
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .fillMaxWidth()
        .height(118.dp)) {
        Column(modifier = Modifier.padding(start = 7.dp, end = 7.dp)) {
            Text (
                stringResource(R.string.moviecollector),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 3.dp, top = 6.dp, bottom = 4.dp)
                    .height(28.dp)
            )
            Row(modifier = Modifier
                .height(66.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(color = MaterialTheme.colorScheme.surface)
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
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 9.dp, top = 0.dp)
                .height(28.dp)
        )
        Text(
            stringResource(text),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
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
    description: String,
    countryFlag: Int,
    countryName: String,
    followers: Int,
    following: Int,
    username: String,
    nameOfUser: String) {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colorScheme.surfaceVariant)
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
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    FollowText(
                        stringResource(R.string.followers),
                        paddingValue = 9.dp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.End
                    )
                    FollowText(
                        followers.toString(),
                        paddingValue = 0.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Start
                    )

                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    FollowText(
                        stringResource(R.string.following),
                        paddingValue = 9.dp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.End
                    )
                    FollowText(
                        following.toString(),
                        paddingValue = 0.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.End
                    )
                }
            }
            Column {
                Text (
                    text = username,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .height(32.dp)
                )
                Row(modifier = Modifier.height(13.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Text (
                        text = nameOfUser,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(start = 10.dp, end = 8.dp)
                    )
                    Image(modifier = Modifier
                        .size(13.dp, 8.dp)
                        .padding(start = 0.dp, top = 0.dp), painter = painterResource(id = countryFlag),
                        contentDescription = "country_flag")
                    Text (
                        text = countryName,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
                Text (
                    text = description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
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
private fun FollowText(text: String, paddingValue: Dp, color: Color, textAlign: TextAlign) {
    Text(
        text = text,
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        color = color,
        textAlign = textAlign,
        modifier = Modifier
            .padding(start = paddingValue)
    )
}