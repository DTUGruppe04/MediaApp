package com.example.mediaapp.apirequests

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {
    //https://api.themoviedb.org/3/trending/movie/week?api_key=4d8b26c7e474409c5926dca8433a8262
    @GET("trending/movie/{timeWindow}?api_key=4d8b26c7e474409c5926dca8433a8262")
    fun getPopularMovie(
        @Path("timeWindow") timeWindow: String,
        @Query("language") language: String
        ) : Call<TMDBMovieResponse>


    //https://api.themoviedb.org/3/search/movie?query=Barbie&api_key=4d8b26c7e474409c5926dca8433a8262
    @GET("search/movie?api_key=4d8b26c7e474409c5926dca8433a8262")
    fun searchForMovie(@Query("query") search: String) : Call<TMDBMovieResponse>

    //https://api.themoviedb.org/3/movie/872585?language=en-US&api_key=4d8b26c7e474409c5926dca8433a8262
    @GET("movie/{id}?api_key=4d8b26c7e474409c5926dca8433a8262")
    fun getMovieDetail(
        @Path("id") id: String,
        @Query("language") language: String
    ) : Call<TMDBMovieDetail>

    //https://api.themoviedb.org/3/movie/872585/similar?language=en-US&page=1&api_key=4d8b26c7e474409c5926dca8433a8262
    @GET("movie/{id}/similar?api_key=4d8b26c7e474409c5926dca8433a8262")
    fun getSimilarMovies(
        @Path("id") id: String,
        @Query("language") language: String
    ) : Call<TMDBMovieResponse>

    //https://api.themoviedb.org/3/movie/872585/reviews?language=en-US&page=1&api_key=4d8b26c7e474409c5926dca8433a8262
    @GET("movie/{id}/reviews?api_key=4d8b26c7e474409c5926dca8433a8262")
    fun getMovieReviews(
        @Path("id") id: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : Call<TMDBMovieReview>

    // https://api.themoviedb.org/3/movie/9445/watch/providers?api_key=4d8b26c7e474409c5926dca8433a8262

    @GET("movie/{id}/watch/providers?api_key=4d8b26c7e474409c5926dca8433a8262")
    fun getWhereToWatchMovie(@Path("id") id: String) : Call<TMDBWhereToWatchMovie>

}