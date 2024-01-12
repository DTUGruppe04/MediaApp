package com.example.mediaapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaapp.backend.database.DatabaseHandler
import com.example.mediaapp.backend.sorting.SortingHandler
import com.example.mediaapp.models.WatchlistMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WatchlistViewModel : ViewModel() {
    private val databaseHandler = DatabaseHandler.getInstance()

    private val _originalWatchlist = MutableStateFlow<List<WatchlistMovie>?>(null)

    private val _filteredWatchList = MutableStateFlow<List<WatchlistMovie>?>(null)
    val filteredWatchList: StateFlow<List<WatchlistMovie>?> = _filteredWatchList.asStateFlow()

    private val _deleteview = MutableStateFlow<Boolean>(false)
    val deleteview: StateFlow<Boolean> = _deleteview.asStateFlow()

    val sortingHandler = SortingHandler()

    fun getWatchlistMovies() {
        viewModelScope.launch {
            val watchlist = databaseHandler.getWatchlistMovies()
            _originalWatchlist.value = watchlist
            _filteredWatchList.value = watchlist
            Log.w("DATABASE CALL", "getWatchlistMovies() Called!")
        }
    }
    fun openDeleteView() {
        _deleteview.value = !_deleteview.value
    }

    fun removeMovieFromWatchlist(movieID: Long) {
        viewModelScope.launch {
            databaseHandler.removeMovieFromWatchlist(movieID)
            val watchlist = databaseHandler.getWatchlistMovies()
            _originalWatchlist.value = watchlist
            _filteredWatchList.value = watchlist
        }
    }
    fun onFilterOptionSelected(filterId: String, option: String) {
        when (filterId) {
            "Genre" -> filterMoviesByGenre(option)
            "Year" -> filterMoviesByDate(option)
            "Name" -> filterMoviesByName(option)
        }
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
            val ascending = order == "Year Asc"
            val filteredMovies = sortingHandler.sortWatchListMoviesByYear(
                _filteredWatchList.value ?: emptyList(), ascending
            )
            _filteredWatchList.value = filteredMovies
        }
    }
    private fun filterMoviesByName(order: String) {
        viewModelScope.launch {
            val ascending = order == "Name Asc"
            val filteredMovies = sortingHandler.sortWatchListMoviesAlphabetically(
                _filteredWatchList.value ?: emptyList(), ascending
            )
            _filteredWatchList.value = filteredMovies
        }
    }
    private fun filterWatchedMovies(isItWatched: Boolean) {
        viewModelScope.launch {
            val filteredMovies = sortingHandler.filterWatchedMovies(
                _originalWatchlist.value ?: emptyList(), isItWatched)
            _filteredWatchList.value = filteredMovies
        }
    }
}