package com.example.mediaapp.backend.repos

import android.util.Log
import com.example.mediaapp.backend.RecommendationEngine
import com.example.mediaapp.backend.apirequests.APIHandler
import com.example.mediaapp.models.Recommend
import com.example.mediaapp.models.Result2
import com.example.mediaapp.models.TMDBMovie

class HomeRepo(
    private val apiHandler: APIHandler,
    private val algorithm: RecommendationEngine
) {
    suspend fun getPopularMovies(timeWindow: String): Result<List<TMDBMovie>> {
        return try {
            val response = apiHandler.getPopularMovies(timeWindow)
            Log.w("API CALL VIEWMODEL", "getPopularMovies() Called!")
            if (response != null && response.total_results > 0) {
                Result.success(response.results)
            } else {
                Result.failure(Exception("No result found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getMoviesInTheatre(): Result<List<Result2>> {
        return try {
            val response = apiHandler.getNowPlayingMovies()
            Log.w("API CALL VIEWMODEL", "getMoviesInTheatre() Called!")
            if (response != null && response.total_results > 0) {
                Result.success(response.results)
            } else {
                Result.failure(Exception("No result found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUpcomingMovies(): Result<List<Result2>> {
        return try {
            val response = apiHandler.getUpcomingMovies()
            Log.w("API CALL VIEWMODEL", "getUpcomingMovies() Called!")
            if (response != null && response.total_results > 0) {
                Result.success(response.results)
            } else {
                Result.failure(Exception("No result found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getRecommendMovies(): Result<List<Recommend>> {
        return try {
            var response = algorithm.getRecommendMovies()
            response = response.shuffled()
            response = response.take(15)
            Log.w("DATABASE CALL VIEWMODEL", "getRecommendMovies() Called!")
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun isUserValid(): Result<Boolean> {
        return try {
            val response = algorithm.isUserValid()
            Log.w("DATABASE CALL VIEWMODEL", "isUserValid() Called!")
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}