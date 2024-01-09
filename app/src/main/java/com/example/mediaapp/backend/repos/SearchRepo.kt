package com.example.mediaapp.backend.repos

import android.util.Log
import com.example.mediaapp.backend.apirequests.APIHandler
import com.example.mediaapp.models.TMDBMovie

class SearchRepository(private val apiHandler: APIHandler) {
    suspend fun searchMovies(query: String): Result<List<TMDBMovie>> {
        return try {
            val response = apiHandler.searchForMovie(query)
            if (response != null && response.total_results > 0) {
                Result.success(response.results)
            } else {
                Result.failure(Exception("No results found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun searchMoviesWithGenre(query: Int): Result<List<TMDBMovie>> {
        return try {
            val response = apiHandler.getMoviesWithGenre(query)
            Log.w("API CALL VIEWMODEL", "getMoviesWithGenre() Called!")
            if (response != null && response.total_results > 0) {
                Result.success(response.results)
            } else {
                Result.failure(Exception("No results found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}