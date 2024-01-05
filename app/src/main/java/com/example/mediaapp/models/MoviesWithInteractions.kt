package com.example.mediaapp.models

data class MoviesWithInteractions(
    val watched: Boolean,
    val recommended: Boolean,
    val rated: Int,
    val movie: TMDBMovie
)
