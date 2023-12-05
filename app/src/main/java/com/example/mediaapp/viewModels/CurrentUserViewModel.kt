package com.example.mediaapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediaapp.models.CurrentUser
import com.example.mediaapp.models.DatabaseHandler
import com.example.mediaapp.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CurrentUserViewModel : ViewModel(){
    private val databaseHandler = DatabaseHandler()
    val user = Firebase.auth.currentUser
    private val _currentUser = MutableStateFlow<CurrentUser?>(null)
    val currentUser: StateFlow<CurrentUser?> = _currentUser.asStateFlow()

    fun getCurrentUser() {
        viewModelScope.launch {
            _currentUser.value = updateCurrentUser()
        }
    }

    private suspend fun updateCurrentUser(): CurrentUser {
        val userFromDatabase = databaseHandler.getUserFromDatabase(user!!.uid)
        return CurrentUser(User.fromMap(userFromDatabase), userFromDatabase["watchlist"] as? List<String> ?: listOf())
    }
}
