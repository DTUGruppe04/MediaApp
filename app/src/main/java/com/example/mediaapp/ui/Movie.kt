package com.example.mediaapp.ui

import androidx.compose.ui.graphics.painter.Painter

data class Movie(
    val title: String = "",
    val genre: String = "",
    val description: String = "",
    val poster: Painter,
    val actor: String = "",
)