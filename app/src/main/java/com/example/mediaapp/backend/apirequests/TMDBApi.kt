package com.example.mediaapp.backend.apirequests

import com.example.mediaapp.models.TMDBMovieCredits
import com.example.mediaapp.models.TMDBMovieDetail
import com.example.mediaapp.models.TMDBMovieResponse
import com.example.mediaapp.models.TMDBMovieReview
import com.example.mediaapp.models.TMDBUpcomingMovies
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

    //https://api.themoviedb.org/3/movie/872585/credits?language=en-US&api_key=4d8b26c7e474409c5926dca8433a8262
    @GET("movie/{id}/credits?api_key=4d8b26c7e474409c5926dca8433a8262")
    fun getMovieCredits(
        @Path("id") id: String,
        @Query("language") language: String
    ) : Call<TMDBMovieCredits>


    //https://api.themoviedb.org/3/movie/872585/recommendations?language=en-US&page=1&api_key=4d8b26c7e474409c5926dca8433a8262
    @GET("movie/{id}/recommendations?api_key=4d8b26c7e474409c5926dca8433a8262")
    fun getMovieSuggestions(
        @Path("id") id: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : Call<TMDBMovieResponse>


    //https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1&api_key=4d8b26c7e474409c5926dca8433a8262
    @GET("movie/top_rated?api_key=4d8b26c7e474409c5926dca8433a8262")
    fun getTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ) : Call<TMDBMovieResponse>

    //https://api.themoviedb.org/3/movie/upcoming?language=en-US&page=1&api_key=4d8b26c7e474409c5926dca8433a8262
    @GET("movie/upcoming?api_key=4d8b26c7e474409c5926dca8433a8262")
    fun getUpcomingMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ) : Call<TMDBUpcomingMovies>

    //https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1&api_key=4d8b26c7e474409c5926dca8433a8262
    @GET("movie/now_playing?api_key=4d8b26c7e474409c5926dca8433a8262")
    fun getNowPlayingMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ) : Call<TMDBUpcomingMovies>
}