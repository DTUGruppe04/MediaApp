package com.example.mediaapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.apirequests.TMDBMovieResponse
import kotlinx.coroutines.launch

class ViewModelPager(): ViewModel() {

    private val apiService = APIHandler()

    val movies = MutableLiveData<TMDBMovieResponse>()

    private fun fetchPopularMovies() {
        viewModelScope.launch {
            val movieList = apiService.getPopularMovie("week")
            if (movieList != null) {
                movies.value = movieList
            }
        }
    }
    init {
        fetchPopularMovies()
    }
}