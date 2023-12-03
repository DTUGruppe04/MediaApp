package com.example.mediaapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaapp.models.CurrentUser
import com.example.mediaapp.models.Stats
import com.example.mediaapp.models.TMDBMovieDetail
import com.example.mediaapp.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class currentUserViewModel : ViewModel(){
    val user = Firebase.auth.currentUser
    val database = Firebase.firestore
    private val _currentUser = MutableStateFlow<CurrentUser?>(null)
    val currentUser: StateFlow<CurrentUser?> = _currentUser.asStateFlow()
    private val _isLoading = MutableStateFlow(false)

    suspend fun updateCurrentUser(): CurrentUser {
        val userFromDatabase = database.collection("users").document(user!!.uid).get().await()

        val username = userFromDatabase.getString("username")!!
        val name = userFromDatabase.getString("name")!!
        val location = userFromDatabase.getString("location")!!
        val followers = userFromDatabase.get("followers") as List<String>
        val following = userFromDatabase.get("following") as List<String>
        val description = userFromDatabase.getString("description")!!
        val profilePicture = userFromDatabase.getString("profilePicture")!!
        val stats = Stats(
            userFromDatabase.getLong("stats.watched")!!,
            userFromDatabase.getLong("stats.reviews")!!,
            userFromDatabase.getLong("stats.rated")!!,
            userFromDatabase.getLong("stats.recommends")!!,
            userFromDatabase.getLong("stats.saved")!!
        )
        val favorites = userFromDatabase.get("favorites") as List<String>
        val recentlyWatched = userFromDatabase.get("recentlyWatched") as List<String>
        val watchlist = userFromDatabase.get("watchlist") as List<String>
        val userObject = User(
            username,
            name,
            location,
            followers,
            following,
            description,
            profilePicture,
            stats,
            favorites,
            recentlyWatched
        )
        return CurrentUser(userObject, watchlist)
        }

    fun getCurrentUser() {
        viewModelScope.launch {
            try {
                val user = updateCurrentUser()
                _currentUser.value = user
            } finally {
                _isLoading.value = false
            }
        }

    }

    }

