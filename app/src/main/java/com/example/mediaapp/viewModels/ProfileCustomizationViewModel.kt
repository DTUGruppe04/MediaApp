package com.example.mediaapp.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mediaapp.backend.database.DatabaseHandler
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class ProfileCustomizationViewModel : ViewModel() {
    private val databaseHandler = DatabaseHandler.getInstance()
    val user = Firebase.auth.currentUser

    private var username = mutableStateOf("")
    var name = mutableStateOf("")
    private var location = mutableStateOf("")
    var description = mutableStateOf("")

    fun updateAccountDetails() {
        val userMap = hashMapOf(
            "username" to username.value,
            "name" to name.value,
            "location" to location.value,
            "description" to description.value
        )
        user?.let { databaseHandler.updateUserInDatabase(it.uid, userMap) }
    }
}