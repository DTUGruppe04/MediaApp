package com.example.mediaapp.backend.apirequests

import android.util.Log
import com.example.mediaapp.models.TMDBMovieCredits
import com.example.mediaapp.models.TMDBMovieDetail
import com.example.mediaapp.models.TMDBMovieResponse
import com.example.mediaapp.models.TMDBMovieReview
import com.example.mediaapp.models.TMDBUpcomingMovies
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.moshi.MoshiConverterFactory

class APIHandler {
    private val api = Retrofit.Builder()
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
    suspend fun getPopularMovies(timeWindow: String, language: String = "en-US") : TMDBMovieResponse? {
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
    suspend fun getUpcomingMovies(language: String = "en-US", page: Int = 1, region: String = "US", type: String = "2|3") : TMDBUpcomingMovies? {
        try {
            val response = api.getUpcomingMovies(language, page, region, type).awaitResponse()
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
    suspend fun getNowPlayingMovies(language: String = "en-US", page: Int = 1, region: String = "US") : TMDBUpcomingMovies? {
        try {
            val response = api.getNowPlayingMovies(language, page, region).awaitResponse()
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

    suspend fun getMoviesWithGenre(genre: Int) : TMDBMovieResponse? {
        try {
            val response = api.getMoviesWithGenre(genre).awaitResponse()
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
     * getGenrebyID
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
    fun getGenrebyID(ids: List<Int>) : List<String> {
        val tempList = mutableListOf<String>()
        for (item in ids) {
            when(item) {
                28 -> tempList.add("Action")
                12 -> tempList.add("Adventure")
                16 -> tempList.add("Animation")
                35 -> tempList.add("Comedy")
                80 -> tempList.add("Crime")
                99 -> tempList.add("Documentary")
                18 -> tempList.add("Drama")
                10751 -> tempList.add("Family")
                14 -> tempList.add("Fantasy")
                36 -> tempList.add("History")
                27 -> tempList.add("Horror")
                10402 -> tempList.add("Music")
                9648 -> tempList.add("Mystery")
                10749 -> tempList.add("Romance")
                878 -> tempList.add("Sci-fi")
                10770 -> tempList.add("TV Movie")
                53 -> tempList.add("Thriller")
                10752 -> tempList.add("War")
                37 -> tempList.add("Western")
            }
        }
        if (tempList.isEmpty()) {
            tempList.add("No Genre")
        }
        return tempList
    }

    fun getIDByGenre(genres: List<String>) : List<Int> {
        val tempList = mutableListOf<Int>()
        for (item in genres) {
            when(item) {
                "Action" -> tempList.add(28)
                "Adventure" -> tempList.add(12)
                "Animation" -> tempList.add(16)
                "Comedy" -> tempList.add(35)
                "Crime" -> tempList.add(80)
                "Documentary" -> tempList.add(99)
                "Drama" -> tempList.add(18)
                "Family" -> tempList.add(10751)
                "Fantasy" -> tempList.add(14)
                "History" -> tempList.add(36)
                "Horror" -> tempList.add(27)
                "Music" -> tempList.add(10402)
                "Mystery" -> tempList.add(9648)
                "Romance" -> tempList.add(10749)
                "Science Fiction" -> tempList.add(878)
                "TV Movie" -> tempList.add(10770)
                "Thriller" -> tempList.add(53)
                "War" -> tempList.add(10752)
                "Western" -> tempList.add(37)
            }
        }
        if (tempList.isEmpty()) {
            tempList.add(-1)
        }
        return tempList
    }
}