package com.example.mediaapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaapp.backend.database.DatabaseHandler
import com.example.mediaapp.models.WatchlistMovie
import com.example.mediaapp.backend.sorting.SortingHandler
import com.example.mediaapp.rating.RatingHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WatchlistViewModel : ViewModel() {
    private val databaseHandler = DatabaseHandler()

    private val _originalWatchlist = MutableStateFlow<List<WatchlistMovie>?>(null)

    private val _filteredWatchList = MutableStateFlow<List<WatchlistMovie>?>(null)
    val filteredWatchList: StateFlow<List<WatchlistMovie>?> = _filteredWatchList.asStateFlow()

    private val _deleteview = MutableStateFlow<Boolean>(false)
    val deleteview: StateFlow<Boolean> = _deleteview.asStateFlow()

    val sortingHandler = SortingHandler()
    val ratingHandler = RatingHandler()

    fun getWatchlistMovies() {
        viewModelScope.launch {
            _originalWatchlist.value = databaseHandler.getWatchlistMovies()
            _filteredWatchList.value = databaseHandler.getWatchlistMovies()
            Log.w("DATABASE CALL", "getWatchlistMovies() Called!")
        }
    }
    fun openDeleteView() {
        _deleteview.value = !_deleteview.value
    }

    fun removeMovieFromWatchlist(movieID: Long) {
        viewModelScope.launch {
            databaseHandler.removeMovieFromWatchlist(movieID)
            _originalWatchlist.value = databaseHandler.getWatchlistMovies()
        }
    }
    fun onFilterOptionSelected(filterId: String, option: String) {
        when (filterId) {
            "Genre" -> filterMoviesByGenre(option)
            "Year" -> filterMoviesByDate(option)
            "Name" -> filterMoviesByName(option)
            "Rating" -> filterMoviesByRating(option)
        }
    }

    private fun filterMoviesByRating(order: String) {

    }

    private fun filterMoviesByGenre(genre: String) {
        viewModelScope.launch {
            val filteredMovies = sortingHandler.filterWatchListMoviesByGenre(
                _originalWatchlist.value ?: emptyList(),
                genre
            )
            _filteredWatchList.value = filteredMovies
        }
    }
    private fun filterMoviesByDate(order: String) {
        viewModelScope.launch {
            val ascending = order == "Ascending"
            val filteredMovies = sortingHandler.sortWatchListMoviesByYear(
                _filteredWatchList.value ?: emptyList(), ascending
            )
            _filteredWatchList.value = filteredMovies
        }
    }
    private fun filterMoviesByName(order: String) {
        viewModelScope.launch {
            val ascending = order == "Ascending"
            val filteredMovies = sortingHandler.sortWatchListMoviesAlphabetically(
                _filteredWatchList.value ?: emptyList(), ascending
            )
            _filteredWatchList.value = filteredMovies
        }
    }
}