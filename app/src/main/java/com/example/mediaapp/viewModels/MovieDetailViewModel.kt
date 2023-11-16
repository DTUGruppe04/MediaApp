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
                val detials = apiHandler.getMovieDetail(movieId)
                _movieDetails.value = detials
            }
            catch (e: Exception){
                _movieDetails.value = null
            }
            finally {
                _isLoading.value = false
            }
        }
    }
}