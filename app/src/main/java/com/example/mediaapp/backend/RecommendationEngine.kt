package com.example.mediaapp.backend

import android.util.Log
import com.example.mediaapp.backend.apirequests.APIHandler
import com.example.mediaapp.backend.database.DatabaseHandler
import com.example.mediaapp.models.Recommend
import com.example.mediaapp.models.WatchlistMovie

class RecommendationEngine {
    private val api = APIHandler()
    private val database = DatabaseHandler.getInstance()
    suspend fun generateMovieSuggestions(movieID: String) {
        Log.w("FUNCTION CALL", "generateMovieSuggestions()")
        val response = api.getMovieSuggestions(movieID)
        val watchlist = database.getWatchlistMovies()
        if (response != null && response.total_results > 0) {
            var i = 0
            var counter = 0
            Log.w("FUNCTION CALL", "generateMovieSuggestions() - While loop")
            while (i < 3 && counter < response.results.size) {
                if (!containMovieId(watchlist, response.results[counter].id.toLong())) {
                    val hash = hashMapOf(
                        "movieID" to response.results[counter].id,
                        "posterPath" to response.results[counter].poster_path,
                        "title" to response.results[counter].title
                    )
                    database.updateRecommendDatabase(hash)
                    i++
                    Log.w("DATABASE CALL", "Added movie to recommendations!")
                }
                counter++
            }
        }
    }

    /**
     * getRecommendMovies
     *
     * Shuffles the list of recommended movies for the user and returns the first 15 of the list
     *
     * @return Returns a shuffle list of 15 recommended movies
     */

    suspend fun getRecommendMovies() : List<Recommend> {
        var response = database.getRecommendMovies()
        if (response.size < 5) {
            return emptyList()
        }
        return database.getRecommendMovies()
    }

    suspend fun removeRecommendMovie(movieID: Long) {
        database.removeMovieRecommend(movieID)
    }


    private fun containMovieId(list: List<WatchlistMovie>, movieID: Long) : Boolean {
        list.forEach {item ->
            if(item.movieID == movieID) {
                return true
            }
        }
        return false
    }

    suspend fun containMovieId(movieID: Long) : Boolean {
        val watchlist = database.getWatchlistMovies()
        watchlist.forEach {item ->
            if(item.movieID == movieID) {
                return true
            }
        }
        return false
    }
}