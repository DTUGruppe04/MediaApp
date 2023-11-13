package com.example.mediaapp.ui

import androidx.compose.ui.graphics.painter.Painter

data class Movie(
    val title: String = "",
    val genres: List<String>,
    val description: String = "",
    val poster: Painter,
    var actors: List<String>,
    var director: String = "",
    var releaseDate: String = "",
    val topImage: Painter,
)


