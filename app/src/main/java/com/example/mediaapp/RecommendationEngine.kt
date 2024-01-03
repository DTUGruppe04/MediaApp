package com.example.mediaapp

import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.DatabaseHandler
import com.example.mediaapp.models.TMDBMovie
import com.example.mediaapp.models.TMDBMovieResponse
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait

class RecommendationEngine {
    private val api = APIHandler()

    private val database = DatabaseHandler()

    suspend fun generateMovieSuggestions(movieID: String) {
        println("Before API call")
        val response = api.getMovieSuggestions(movieID)
        println("After API call")
        if (response != null && response.total_results > 0) {
            for (i in 0..2) {
                val hash = hashMapOf(
                    "movieID" to response.results[i].id,
                    "posterPath" to response.results[i].poster_path,
                    "title" to response.results[i].title
                )
                database.updateRecommendDatabase(hash)
            }
        }
    }
}