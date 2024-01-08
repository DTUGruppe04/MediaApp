package com.example.mediaapp.backend

import com.example.mediaapp.backend.apirequests.APIHandler
import com.example.mediaapp.backend.database.DatabaseHandler
import com.example.mediaapp.models.Recommend
import com.example.mediaapp.models.WatchlistMovie

class RecommendationEngine {
    private val api = APIHandler()
    private val database = DatabaseHandler.getInstance()
    suspend fun generateMovieSuggestions(movieID: String) {
        val response = api.getMovieSuggestions(movieID)
        val watchlist = database.getWatchlistMovies()
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

    /**
     * getRecommendMovies
     *
     * Shuffles the list of recommended movies for the user and returns the first 15 of the list
     *
     * @return Returns a shuffle list of 15 recommended movies
     */

    suspend fun getRecommendMovies() : List<Recommend> {
        return database.getRecommendMovies()
    }

    suspend fun removeRecommendMovie(movieID: Long) {
        database.removeMovieRecommend(movieID)
    }

    private fun containMovieId(list: List<WatchlistMovie>, movieID: Long) : Boolean {
        list.forEach {item ->
            println("WatchID: ${item.movieID}")
            println("RecommendID: $movieID")
            if(item.movieID == movieID) {
                return true
            }
        }
        return false
    }

    /**
     * isUserValid
     *
     * This function determines if a user is eligible for personal recommendations.
     * Users with less than 5 movies on their recommendation list are not eligible yet.
     *
     * @return Returns true if valid and false otherwise
     */
    suspend fun isUserValid(): Boolean {
        val databaseResponse = database.getRecommendMovies()
        return databaseResponse.size > 4
    }

}