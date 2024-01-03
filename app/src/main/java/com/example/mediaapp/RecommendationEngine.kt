package com.example.mediaapp

import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.DatabaseHandler
import com.example.mediaapp.models.TMDBMovie
import com.example.mediaapp.models.TMDBMovieResponse
import kotlinx.coroutines.runBlocking

class RecommendationEngine {
    private val api = APIHandler()

    private val database = DatabaseHandler()

    private var suggestionMovies: TMDBMovieResponse? = null
    private fun createHashMap(movie: TMDBMovie) = hashMapOf(
        "movieID" to movie.id,
        "posterPath" to movie.poster_path,
        "title" to movie.title
    )
    fun generateMovieSuggestions(movieID: String) {
        runBlocking {
            suggestionMovies = api.getMovieSuggestions(movieID)

            for (i in 0..2) {
                database.updateRecommendDatabase(createHashMap(suggestionMovies!!.results[0]))
            }
        }
    }
}