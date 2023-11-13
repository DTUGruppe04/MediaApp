package com.example.mediaapp.apirequests

data class TMDBMovie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class TMDBMovieResponse(
    val page: Int,
    val results: List<TMDBMovie>,
    val total_pages: Int,
    val total_results: Int
)