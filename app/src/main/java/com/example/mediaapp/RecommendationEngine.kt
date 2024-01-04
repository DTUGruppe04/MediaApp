package com.example.mediaapp

import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.DatabaseHandler
import com.example.mediaapp.models.TMDBMovie
import com.example.mediaapp.models.TMDBMovieResponse
import com.example.mediaapp.models.WatchlistMovie
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait

class RecommendationEngine {
    private val api = APIHandler()
    private val database = DatabaseHandler()
    suspend fun generateMovieSuggestions(movieID: String) {
        val response = api.getMovieSuggestions(movieID)
        var watchlist = database.getWatchlistMovies()
        if (response != null && response.total_results > 0) {
            var i = 0
            var counter = 0
            while (i < 3 && counter < response.results.size) {
                if (!containMovieId(watchlist, response.results[counter].id.toLong())) {
                    val hash = hashMapOf(
                        "movieID" to response.results[counter].id,
                        "posterPath" to response.results[counter].poster_path,
                        "title" to response.results[counter].title
                    )
                    database.updateRecommendDatabase(hash)
                    i++
                }
                counter++
            }
        }
    }

    //Helper function
    fun containMovieId(list: List<WatchlistMovie>, movieID: Long) : Boolean {
        list.forEach {item ->
            println("WatchID: ${item.movieID}")
            println("RecommendID: $movieID")
            if(item.movieID == movieID) {
                return true
            }
        }
        return false
    }
}