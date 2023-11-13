package com.example.mediaapp.apirequests

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDBApi {
    @GET("trending/movie/week?api_key=4d8b26c7e474409c5926dca8433a8262")
    fun getPopularMovie(
        /*@Path("timeWindow") timeWindow: String,*/
        /*@Path("key") apiKey: String */
        ) : Call<TMDBMovieResponse>
}

// https://api.themoviedb.org/3/trending/movie/week?api_key=4d8b26c7e474409c5926dca8433a8262