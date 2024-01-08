package com.example.mediaapp.backend.repos

import com.example.mediaapp.backend.apirequests.APIHandler
import com.example.mediaapp.models.TMDBMovieCredits
import com.example.mediaapp.models.TMDBMovieDetail


class MovieDetailRepo(private val apiHandler: APIHandler) {
    suspend fun getMovieDetails(movieId: String): TMDBMovieDetail? {
        return try {
            apiHandler.getMovieDetail(movieId)
        } catch (e: Exception) {
            null
        }
    }
    suspend fun getMovieCredits(movieId: String): TMDBMovieCredits? {
        return try {
            apiHandler.getMovieCredits(movieId)
        } catch (e: Exception) {
            null
        }
    }
}