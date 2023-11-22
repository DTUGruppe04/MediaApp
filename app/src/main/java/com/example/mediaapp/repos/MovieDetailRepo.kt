package com.example.mediaapp.repos

import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.TMDBMovieDetail


class MovieDetailRepo(private val apiHandler: APIHandler) {
    suspend fun getMovieDetails(movieId: String): TMDBMovieDetail? {
        return try {
            apiHandler.getMovieDetail(movieId)
        } catch (e: Exception) {
            null
        }
    }
}