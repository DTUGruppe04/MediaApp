package com.example.mediaapp.apirequests

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class APIHandler {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    fun call() {
        val TMDBPopularService = retrofit.create<popularMovie>()
        Log.d("API", TMDBPopularService.title)
    }
}