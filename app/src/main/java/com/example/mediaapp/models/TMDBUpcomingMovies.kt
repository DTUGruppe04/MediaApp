package com.example.mediaapp.models

// Data Structure for getUpcomingMovies and getNowPlayingMovies
data class TMDBUpcomingMovies(
    val dates: Dates,
    val page: Int,
    val results: List<Result2>,
    val total_pages: Int,
    val total_results: Int
)