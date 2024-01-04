package com.example.mediaapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaapp.models.DatabaseHandler
import com.example.mediaapp.models.WatchlistMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WatchlistViewModel : ViewModel(){
    private val databaseHandler = DatabaseHandler()
    private val _watchList = MutableStateFlow<List<WatchlistMovie>?>(null)
    val watchList: StateFlow<List<WatchlistMovie>?> = _watchList.asStateFlow()

    fun getWatchlistMovies() {
        viewModelScope.launch {
            _watchList.value = databaseHandler.getWatchlistMovies()
        }
    }

}