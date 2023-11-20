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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mediaapp.Screen
import com.example.mediaapp.models.TMDBMovie

object SearchQueryLayout {
    @Composable
    fun SearchQueryList(movies: List<TMDBMovie>, navController: NavController) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            items(movies) { movie ->
                SearchQueryListItem(
                    title = movie.title,
                    description = movie.overview,
                    posterURL = movie.poster_path,
                    navController = navController
                )
                Divider(
                    color = MaterialTheme.colorScheme.outline,
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(start = 2.dp, end = 2.dp)
                )
            }
        }
    }
    @Composable
    fun SearchQueryListItem(title: String, description: String, posterURL: String, navController: NavController) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 24.dp, top = 8.dp, bottom = 8.dp)
                .clickable {
                    navController.navigate(Screen.MoviePage.route)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = posterURL),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .composeImageModifier()
            )
            Column (
                modifier = Modifier.padding(start = 16.dp)
            ){
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = description, style = MaterialTheme.typography.titleSmall)
            }
        }
    }
    @Composable
    private fun Modifier.composeImageModifier(): Modifier {
        return this
            .shadow(elevation = 4.dp, spotColor = MaterialTheme.colorScheme.background)
            .border(1.dp, Color(0xFF000000), RoundedCornerShape(10.dp))
            .padding(0.5.dp)
            .width(96.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(142.dp)
    }
}