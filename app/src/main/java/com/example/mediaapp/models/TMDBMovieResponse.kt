package com.example.mediaapp.models

data class TMDBMovieResponse(
    val page: Int,
    val results: List<TMDBMovie>,
    val total_pages: Int,
    val total_results: Int
)