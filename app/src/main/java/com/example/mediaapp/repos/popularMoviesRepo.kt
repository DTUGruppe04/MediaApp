package com.example.mediaapp.repos

import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.TMDBMovie

class popularMoviesRepo(private val apiHandler: APIHandler) {
    suspend fun getPopularMovies(timeWindow: String): Result<List<TMDBMovie>> {
        return try {
            val response = apiHandler.getPopularMovies(timeWindow)
            if (response != null && response.total_results > 0) {
                Result.success(response.results)
            } else {
                Result.failure(Exception("No result found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}