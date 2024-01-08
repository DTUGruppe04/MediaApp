package com.example.mediaapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaapp.backend.database.DatabaseHandler
import com.example.mediaapp.models.WatchlistMovie
import com.example.mediaapp.backend.sorting.SortingHandler
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
    fun filterMoviesByGenre(genre: String) {
        viewModelScope.launch {
            val filteredMovies = sortingHandler.filterWatchListMoviesByGenre(
                _originalWatchlist.value ?: emptyList(),
                genre
            )
            _filteredWatchList.value = filteredMovies
        }
    }
}