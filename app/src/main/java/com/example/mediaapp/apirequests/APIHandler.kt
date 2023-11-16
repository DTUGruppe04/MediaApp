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

    /**
     * getPopularMovieData
     *
     * This function retrieves Popular Movie Data JSON from the TMDB API Database and serialize
     * the JSON into usable Data Objects.
     *
     * @param timeWindow (Required) Specifies the time window for which popular movie data is to be retrived.
     * Accepts 'day' or 'week' as valid inputs.
     * @param language (Optional) Specifies the language for the movie data. If not provided,
     * the default language is 'en-US'.
     *
     * @return Returns a Data Object containing popular movie data from the TMDB API.
     * If return is null something went wrong.
     *
     */
    suspend fun getPopularMovie(timeWindow: String, language: String = "en-US") : TMDBMovieResponse? {
        try {
            val response = api.getPopularMovie(timeWindow, language).awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    return data
                }
            }
            return null
        } catch (e: Exception) {
            Log.e("APIError", e.toString())
            return null
        }
    }

    /**
     * searchForMovie
     *
     * This function retrieves Search Movie Data JSON from TMDB API Database and serialize
     * the JSON into usable Data Objects.
     *
     * @param search (Required) Search for movies that matches string search.
     *
     * @return Returns a Data Objects containing search results. If return is null something went wrong
     */
    suspend fun searchForMovie(search: String) : TMDBMovieResponse? {
        try {
            val response = api.searchForMovie(search).awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null && data.total_results != 0) {
                    return data
                }
            }
            return null
        } catch (e: Exception) {
            Log.e("APIError", e.toString())
            return null
        }
    }

    /**
     * getMovieDetail
     *
     * This function retrieves Movie Details as JSON and serialize
     * the JSON into usable Data Objects.
     *
     * @param id (Required) The id of the specific movie
     * @param language (Optional) Specifies the language for the movie data. If not provided,
     * the default language is 'en-US'.
     *
     * @return Returns a Data Object containing Movie Details. If return is null something went wrong
     */
    suspend fun getMovieDetail(id: String, language: String = "en-US") : TMDBMovieDetail? {
        try {
            val response = api.getMovieDetail(id, language).awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    return data
                }
            }
            return null
        } catch (e: Error) {
            Log.e("APIError", e.toString())
            return null
        }
    }

    /**
     * getSimilarMovies
     *
     * This function retrieves similar movies as JSON and serialize
     * the JSON into usable Data Objects. The API uses keywords as genres, cast and directors to determine
     * similar movies.
     *
     * @param id (Required) The id of the specific movie
     * @param language (Optional) Specifies the language for the movie data. If not provided,
     * the default language is 'en-US'.
     *
     * @return Returns a Data Objects containing similar movies. If return is null something went wrong
     */
    suspend fun getSimilarMovies(id: String, language: String = "en-US") : TMDBMovieResponse? {
        try {
            val response = api.getSimilarMovies(id, language).awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null && data.total_results != 0) {
                    return data
                }
            }
            return null
        } catch (e: Exception) {
            Log.e("APIError", e.toString())
            return null
        }
    }

    /**
     * getMovieReviews
     *
     * This function retrieves reviews of a specific movie as JSON and serialize
     * the JSON into usable Data Objects.
     *
     * @param id (Required) The id of the specific movie
     * @param language (Optional) Specifies the language for the movie data. If not provided,
     * the default language is 'en-US'.
     * @param page (Optional) Looking for new data of increase int page. If not provided,
     * the default int page is 1
     *
     * @return Returns a Data Objects containing reviews for a specific movie.
     * If return is null something went wrong
     */
    suspend fun getMovieReviews(id: String, language: String = "en-US", page: Int = 1) : TMDBMovieReview? {
        try {
            val response = api.getMovieReviews(id, language, page).awaitResponse()
            if (response.isSuccessful) {
                var data = response.body()
                if (data != null && data.total_results != 0) {
                    return data
                }
            }
            return null
        } catch (e: Exception) {
            Log.e("APIError", e.toString())
            return null
        }
    }

    /**
     * getMovieCredits
     *
     * This function retrieves credits for a specific movie as JSON and serialize
     * the JSON into usable Data Objects.
     *
     * @param id (Required) The id of the specific movie
     * @param language (Optional) Specifies the language for the movie data. If not provided,
     * the default language is 'en-US'.
     *
     * @return Returns a Data Objects containing credits for a specific movie.
     * If return is null something went wrong
     */
    suspend fun getMovieCredits(id: String, language: String = "en-US") : TMDBMovieCredits? {
        try {
            val response = api.getMovieCredits(id, language).awaitResponse()
            if (response.isSuccessful) {
                var data = response.body()
                if (data != null) {
                    return data
                }
            }
            return null
        } catch (e: Exception) {
            Log.e("APIError", e.toString())
            return null
        }
    }

    /**
     * getMovieSuggestions
     *
     * This function retrieves suggestions based on a specific movie as JSON and serialize
     * the JSON into usable Data Objects. The suggestions are based on user ratings and favorite
     * data from users.
     *
     * @param id (Required) The id of the specific movie
     * @param language (Optional) Specifies the language for the movie data. If not provided,
     * the default language is 'en-US'.
     * @param page (Optional) Looking for new data of increase int page. If not provided,
     * the default int page is 1
     *
     * @return Returns a Data Objects containing suggestions of movies.
     * If return is null something went wrong
     */
    suspend fun getMovieSuggestions(id: String, language: String = "en-US", page: Int = 1) : TMDBMovieResponse? {
        try {
            val response = api.getMovieSuggestions(id, language, page).awaitResponse()
            if (response.isSuccessful) {
                var data = response.body()
                if (data != null && data.total_results != 0) {
                    return data
                }
            }
            return null
        } catch (e: Exception) {
            Log.e("APIError", e.toString())
            return null
        }
    }

    /**
     * getTopRatedMovies
     *
     * This function retrieves top rated movies as JSON and serialize
     * the JSON into usable Data Objects.
     *
     * @param language (Optional) Specifies the language for the movie data. If not provided,
     * the default language is 'en-US'.
     * @param page (Optional) Looking for new data of increase int page. If not provided,
     * the default int page is 1
     *
     * @return Returns a Data Objects containing top rated movies.
     * If return is null something went wrong
     */
    suspend fun getTopRatedMovies(language: String = "en-US", page: Int = 1) : TMDBMovieResponse? {
        try {
            val response = api.getTopRatedMovies(language, page).awaitResponse()
            if (response.isSuccessful) {
                var data = response.body()
                if (data != null && data.total_results != 0) {
                    return data
                }
            }
            return null
        } catch (e: Exception) {
            Log.e("APIError", e.toString())
            return null
        }
    }

    /**
     * getUpcomingMovies
     *
     * This function retrieves top rated movies as JSON and serialize
     * the JSON into usable Data Objects.
     *
     * @param language (Optional) Specifies the language for the movie data. If not provided,
     * the default language is 'en-US'.
     * @param page (Optional) Looking for new data of increase int page. If not provided,
     * the default int page is 1
     *
     * @return Returns a Data Objects containing upcoming movies.
     * If return is null something went wrong
     */
    suspend fun getUpcomingMovies(language: String = "en-US", page: Int = 1) : TMDBUpcomingMovies? {
        try {
            val response = api.getUpcomingMovies(language, page).awaitResponse()
            if (response.isSuccessful) {
                var data = response.body()
                if (data != null && data.total_results != 0) {
                    return data
                }
            }
            return null
        } catch (e: Exception) {
            Log.e("APIError", e.toString())
            return null
        }
    }

    /**
     * getNowPlayingMovies
     *
     * This function retrieves movies currently playing in theatres as JSON and serialize
     * the JSON into usable Data Objects.
     *
     * @param language (Optional) Specifies the language for the movie data. If not provided,
     * the default language is 'en-US'.
     * @param page (Optional) Looking for new data of increase int page. If not provided,
     * the default int page is 1
     *
     * @return Returns a Data Objects containing movies currently playing in theatres.
     * If return is null something went wrong
     */
    suspend fun getNowPlayingMovies(language: String = "en-US", page: Int = 1) : TMDBUpcomingMovies? {
        try {
            val response = api.getNowPlayingMovies(language, page).awaitResponse()
            if (response.isSuccessful) {
                var data = response.body()
                if (data != null && data.total_results != 0) {
                    return data
                }
            }
            return null
        } catch (e: Exception) {
            Log.e("APIError", e.toString())
            return null
        }
    }

    /*
    //Function isnt done yet
    suspend fun getWhereToWatchMovie(id: String) : TMDBWhereToWatchMovie? {
        try {
            val response = api.getWhereToWatchMovie(id).awaitResponse()
            if (response.isSuccessful) {
                var data = response.body()
                if (data != null) {
                    return data
                }
            }
            return null
        } catch (e: Exception) {
            Log.e("APIError", e.toString())
            return null
        }
    }
     */

}