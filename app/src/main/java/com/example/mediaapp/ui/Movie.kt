package com.example.mediaapp.ui

import androidx.compose.ui.graphics.painter.Painter

data class Movie(
    val title: String = "",
    val genres: List<String>,
    val description: String = "",
    val poster: String = "",
    var actors: List<String>,
    var director: String = "",
    var releaseDate: String = "",
    val topImage: Painter,
) {
    companion object {
        fun fromMap(map: Map<String, Any?>): Movie {
            return Movie(
                map["title"] as? String ?: "",
                map["genres"] as? List<String> ?: listOf(),
                map["description"] as? String ?: "",
                map["poster"] as String,
                map["actors"] as? List<String> ?: listOf(),
                map["director"] as? String ?: "",
                map["releaseDate"] as? String ?: "",
                map["topImage"] as Painter
            )
        }
    }
}


