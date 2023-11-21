package com.example.mediaapp.models

data class TMDBMovieCredits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)