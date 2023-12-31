package com.example.mediaapp.models

data class TMDBMovieReview(
    val id: Int,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)