package com.example.mediaapp.repos

import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.TMDBMovieDetail

class MovieDetailRepository(private val apiHandler: APIHandler) {

    /*suspend fun getMovieDetails(movieId: Int): Result<TMDBMovieDetail> {
        return try {
            val response = apiHandler.getMovieDetail(movieId.toString())
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error fetching movie details"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }*/
}
