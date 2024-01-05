package com.example.mediaapp.viewModels

interface SortableViewmodel {
    //fun filterMoviesByGenre(genre: String): List<WatchlistMovie>
    fun filterMoviesByGenre(genre: String): List<Map<String, Any?>>
}