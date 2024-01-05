package com.example.mediaapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaapp.backend.database.DatabaseHandler
import com.example.mediaapp.models.WatchlistMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WatchlistViewModel : ViewModel(){
    private val databaseHandler = DatabaseHandler()
    private val _watchList = MutableStateFlow<List<WatchlistMovie>?>(null)
    val watchList: StateFlow<List<WatchlistMovie>?> = _watchList.asStateFlow()
    private val _deleteview = MutableStateFlow<Boolean>(false)
    val deleteview: StateFlow<Boolean> = _deleteview.asStateFlow()

    fun getWatchlistMovies() {
        viewModelScope.launch {
            Log.w("DATA BASE CALL", "getWatchlistMovies() Called!")
            _watchList.value = databaseHandler.getWatchlistMovies()
        }
    }

    fun openDeleteView() {
        _deleteview.value = !_deleteview.value
    }

    fun removeMovieFromWatchlist(movieID: Long) {
        viewModelScope.launch {
            databaseHandler.removeMovieFromWatchlist(movieID)
            _watchList.value = databaseHandler.getWatchlistMovies()
        }
    }

}