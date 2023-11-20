package com.example.mediaapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.models.TMDBMovieDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val apiHandler: APIHandler) : ViewModel() {
    private val _movieDetails = MutableStateFlow<TMDBMovieDetail?>(null)
    val movieDetails: StateFlow<TMDBMovieDetail?> = _movieDetails.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun fetchMovieDetails(movieId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val details = apiHandler.getMovieDetail(movieId)
                _movieDetails.value = details
            } catch (e: Exception) {
                _movieDetails.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    /*
    Implement later when watchlist is functional

    private val _isInWatchlist = MutableStateFlow(false)
    val isInWatchlist: StateFlow<Boolean> = _isInWatchlist.asStateFlow()

    fun addToWatchlist(movieId: String) {
        viewModelScope.launch {
            try {
                _isInWatchlist.value = true
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }

    fun removeFromWatchlist(movieId: String) {
        viewModelScope.launch {
            try {
                _isInWatchlist.value = false
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }
    */
}