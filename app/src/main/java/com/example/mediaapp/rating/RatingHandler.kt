package com.example.mediaapp.rating

import com.example.mediaapp.backend.RecommendationEngine
import com.example.mediaapp.backend.database.DatabaseHandler
import com.example.mediaapp.models.RatingAverage
import com.example.mediaapp.models.RatingForDatabase

class RatingHandler {
    private val databaseHandler = DatabaseHandler.getInstance()
    private val recommendationEngine = RecommendationEngine()
    suspend fun getRating(movieID: Long): RatingAverage {
        val rating = databaseHandler.getRatedMovie(movieID.toString())
        val amount = rating.size
        val average = rating.map { it.rating }.average()

        return RatingAverage(movieID, average, amount)
    }

    suspend fun addRating(movieID: Long, rating: Int) {
        val ratingForDatabase = RatingForDatabase(movieID, rating.toLong())
        databaseHandler.updateRatedMovies(movieID.toString(), ratingForDatabase)
        databaseHandler.updateRatedMoviesUser(movieID.toString(), ratingForDatabase)
        if (rating >= 7) {
            recommendationEngine.generateMovieSuggestions(movieID.toString())
        }
    }
}