package com.example.mediaapp.rating

import com.example.mediaapp.models.DatabaseHandler
import com.example.mediaapp.models.RatingAverage
import com.example.mediaapp.models.RatingForDatabase

class RatingHandler {
    private val databaseHandler = DatabaseHandler()
    suspend fun getRating(movieID: Long): RatingAverage {
        val rating = databaseHandler.getRatedMovie(movieID.toString())
        val amount = rating.size
        val average = rating.map { it.rating }.average()

        return RatingAverage(movieID, average, amount)
    }

    suspend fun addRating(movieID: Long, rating: Int) {
        val ratingForDatabase = RatingForDatabase(movieID, rating)
        databaseHandler.updateRatedMovies(movieID.toString(), ratingForDatabase)
        databaseHandler.updateRatedMoviesUser(movieID.toString(), ratingForDatabase)
    }

}