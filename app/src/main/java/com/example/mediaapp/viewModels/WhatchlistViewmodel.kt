package com.example.mediaapp.viewModels

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaapp.models.CurrentUser
import com.example.mediaapp.models.DatabaseHandler
import com.example.mediaapp.models.User
import com.example.mediaapp.models.WatchlistMovie
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WhatchlistViewModel : ViewModel(){
    private val databaseHandler = DatabaseHandler()
    private val _watchList = MutableStateFlow<List<WatchlistMovie>?>(null)
    val watchList: StateFlow<List<WatchlistMovie>?> = _watchList.asStateFlow()

    fun getWatchlistMovies() {
        viewModelScope.launch {
            _watchList.value = databaseHandler.getWatchlistMovies()
        }
    }

}