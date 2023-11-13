package com.example.mediaapp.apirequests

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


class APIHandler {
    val api = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(TMDBApi::class.java)

    suspend fun getPopularMovieData() : TMDBMovieResponse? {
            val response = api.getPopularMovie().awaitResponse()
            if(response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    return data
                }
            }
            return null
    }

    /*
    @SuppressLint("CoroutineCreationDuringComposition")
    suspend fun getPopularMovieData(scope: CoroutineScope) : TMDBMovieResponse {
        scope.launch(Dispatchers.IO) {
            val response = api.getPopularMovie().awaitResponse()
            if(response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    return data
                }
            }
            return null
        }
    }

     */
}