package com.example.mediaapp.models

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class DatabaseHandler {
    private val database = Firebase.firestore
    private val userCache = mutableMapOf<String, Map<String, Any?>>()

    suspend fun getUserFromDatabase(userId: String): Map<String, Any?> {
        // Check if the user data is in the cache
        userCache[userId]?.let { cachedUserData ->
            return cachedUserData
        }

        // If not in the cache, get it from the database
        val userData = database.collection("users").document(userId).get().await().data ?: mapOf()

        // Store the user data in the cache for future use
        userCache[userId] = userData

        return userData
    }

    fun updateUserInDatabase(userId: String, userMap: Map<String, Any?>) {
        // Update the user data in the database
        database.collection("users").document(userId).set(userMap)

        // Also update the user data in the cache
        userCache[userId] = userMap
    }
}