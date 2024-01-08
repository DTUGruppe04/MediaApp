package com.example.mediaapp.backend.sorting

import com.example.mediaapp.models.WatchlistMovie

class sortingHandler {
    fun sortMoviesAlphabetically(movies: List<WatchlistMovie>, ascending: Boolean = true): List<WatchlistMovie> {
        return if (ascending) {
            movies.sortedBy { it.title }
        } else {
            movies.sortedByDescending { it.title }
        }
    }

    fun filterMoviesByGenre(movies: List<WatchlistMovie>, genre: String): List<WatchlistMovie> {
        return movies.filter { movie -> movie.genres.any { it.name == genre } }
    }
}