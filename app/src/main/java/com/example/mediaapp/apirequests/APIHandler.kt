package com.example.mediaapp.apirequests

import android.util.Log
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.moshi.MoshiConverterFactory

class APIHandler {
    val api = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TMDBApi::class.java)


    suspend fun getPopularMovieData(timeWindow: String, language: String = "en-US") : TMDBMovieResponse? {
        try {
            val response = api.getPopularMovie(timeWindow, language).awaitResponse()
            if(response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    return data
                }
            }
            return null
        } catch (e: Exception) {
            return null
            Log.e("APIError", e.toString())
        }
    }
}