package com.example.mediaapp.ui

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediaapp.R
import com.example.mediaapp.Screen
import androidx.compose.ui.tooling.preview.Preview as Preview1

class SearchQueryLayout(private val movies: List<MovieListLayout.Movie>) {
    @Composable
    fun SearchQueryList(navController: NavController){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2B2930)),
        ) {
            items(movies) { movie ->
                SearchQueryListItem(
                    title = movie.title,
                    actor = movie.actor,
                    description = movie.description,
                    poster = movie.poster,
                    navController = navController
                )
                Divider(
                    color = Color.Gray,
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(start = 2.dp, end = 2.dp)
                )
            }
        }
    }
    @Composable
    fun SearchQueryListItem(title: String, actor: String, description: String, poster: Painter, navController: NavController) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp, top = 8.dp, bottom = 8.dp)
                .clickable {
                    navController.navigate(Screen.MoviePage.route)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = poster,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFF000000),
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .width(45.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .height(67.dp),
            )
            Column (
                modifier = Modifier.padding(start = 16.dp)
            ){
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFCAC4D0),

                        letterSpacing = 0.25.sp,
                    )
                )
                Text(
                    text = actor,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFCAC4D0),
                        letterSpacing = 0.25.sp,
                    )
                )
                Text(
                    text = description,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFCAC4D0),
                        letterSpacing = 0.25.sp,
                    )
                )
            }
        }
    }
    @Preview1
    @Composable
    fun SearchQueryPreview(navController: NavController) {
        val movies = listOf(
            MovieListLayout.Movie(
                stringResource(R.string.zohan),
                stringResource(R.string.adventure),
                stringResource(R.string.recommended_by_david_and_4_others), painterResource(id = R.drawable.poster_zohan)
            ),
            MovieListLayout.Movie(
                stringResource(R.string.borat),
                stringResource(R.string.comedy),
                stringResource(R.string.recommended_by_mikkel_and_2_others), painterResource(id = R.drawable.poster_borat)
            ),
            MovieListLayout.Movie(
                stringResource(R.string.step_brothers),
                stringResource(R.string.comedy),
                stringResource(R.string.recommended_by_jonathan), painterResource(id = R.drawable.poster_stepbrothers)
            )
        )
        val searchLayout = SearchQueryLayout(movies)
        searchLayout.SearchQueryList(navController = navController)
    }

}