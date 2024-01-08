package com.example.mediaapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.DatabaseHandler
import com.example.mediaapp.models.RatingAverage
import com.example.mediaapp.backend.apirequests.APIHandler
import com.example.mediaapp.backend.database.DatabaseHandler
import com.example.mediaapp.models.TMDBMovieCredits
import com.example.mediaapp.models.TMDBMovieDetail
import com.example.mediaapp.rating.RatingHandler
import com.example.mediaapp.repos.MovieDetailRepo
import com.example.mediaapp.backend.repos.MovieDetailRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel() : ViewModel() {

    private val apiHandler = APIHandler()
    private val RatingHandler = RatingHandler()
    private val movieDetailRepo = MovieDetailRepo(apiHandler)

    private val _movieDetails = MutableStateFlow<TMDBMovieDetail?>(null)
    val movieDetails: StateFlow<TMDBMovieDetail?> = _movieDetails.asStateFlow()

    private val _movieCredits = MutableStateFlow<TMDBMovieCredits?>(null)
    val movieCredits: StateFlow<TMDBMovieCredits?> = _movieCredits.asStateFlow()

    private val _movieRating = MutableStateFlow<RatingAverage?>(null)
    val movieRating: StateFlow<RatingAverage?> = _movieRating.asStateFlow()

    private val _isLoading = MutableStateFlow(false)

    private val databaseHandler = DatabaseHandler()

    fun fetchMovieDetails(movieId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val details = movieDetailRepo.getMovieDetails(movieId)
                _movieDetails.value = details
            } finally {
                _isLoading.value = false
            }
        }
    }
    fun fetchMovieCredits(movieId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val credits = movieDetailRepo.getMovieCredits(movieId)
                _movieCredits.value = credits
            } finally {
                _isLoading.value = false
            }
        }
    }

    private val _isInWatchlist = MutableStateFlow(false)
    val isInWatchlist: StateFlow<Boolean> = _isInWatchlist.asStateFlow()

    fun checkIfInWatchlist(movieId: String) {
        viewModelScope.launch {
            try {
                val watchlistMovies = databaseHandler.getWatchlistMovies()
                _isInWatchlist.value = watchlistMovies.any { it.movieID == movieId.toLong() }
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }

    fun addToWatchlist(movieId: String) {
        viewModelScope.launch {
            try {
                databaseHandler.updateWatchlistMovie(createWatchlistMap())
                _isInWatchlist.value = true
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }

    fun removeFromWatchlist(movieId: String) {
        viewModelScope.launch {
            try {
                databaseHandler.removeMovieFromWatchlist(movieId.toLong())
                _isInWatchlist.value = false
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }

    fun updateRating(movieId: String) {
        viewModelScope.launch {
            try {
                _movieRating.value = RatingHandler.getRating(movieId.toLong())
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }

    fun rateMovie(movieId: String, rating: Int) {
        viewModelScope.launch {
            try {
                RatingHandler.addRating(movieId.toLong(), rating)
                _movieRating.value = RatingHandler.getRating(movieId.toLong())
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }

    private fun createWatchlistMap() = hashMapOf(
        "movieID" to movieDetails.value?.id,
        "posterPath" to movieDetails.value?.poster_path,
        "title" to movieDetails.value?.title,
        "genres" to movieDetails.value?.genres,
        "description" to movieDetails.value?.overview
    )
}
