package com.example.mediaapp.sorting

import com.example.mediaapp.models.TMDBMovie
import com.example.mediaapp.models.WatchlistMovie

class SortingHandler {
    fun sortWatchListMoviesAlphabetically(movies: List<WatchlistMovie>, ascending: Boolean = true): List<WatchlistMovie> {
        return if (ascending) {
            movies.sortedBy { it.title }
        } else {
            movies.sortedByDescending { it.title }
        }
    }

    fun sortTMDBMoviesAlphabetically(movies: List<TMDBMovie>, ascending: Boolean = true): List<TMDBMovie> {
        return if (ascending) {
            movies.sortedBy { it.title }
        } else {
            movies.sortedByDescending { it.title }
        }
    }

    fun filterWatchListMoviesByGenre(movies: List<WatchlistMovie>, genre: String): List<WatchlistMovie> {
        return movies.filter { movie -> movie.genres.any { it.name == genre } }
    }

    //implement when sort works
    /*
    fun filterTMDBMoviesByGenre(movies: List<TMDBMovie>, genre: String): List<TMDBMovie> {
        return movies.filter { movie -> movie.genres.any { it.name == genre } }
    }*/
}