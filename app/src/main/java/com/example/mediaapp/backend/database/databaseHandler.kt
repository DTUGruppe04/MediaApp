package com.example.mediaapp.backend.database

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mediaapp.models.Recommend
import com.example.mediaapp.models.WatchlistMovie
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

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

    fun getCurrentUserID(): String? {
        return Firebase.auth.currentUser?.uid
    }

    fun updateWatchlistMovie(watchlistMovieMap: Map<String, Any?>) {
        getCurrentUserID()?.let { database.collection("users")
            .document(it)
            .collection("watchlist")
            .document(watchlistMovieMap["movieID"]
                .toString())
            .set(watchlistMovieMap) }
    }

    suspend fun getWatchlistMovies(): List<WatchlistMovie> = withContext(Dispatchers.IO) {
        val watchlistMovies = mutableListOf<WatchlistMovie>()
        getCurrentUserID()?.let { userID ->
            val result = database.collection("users")
                .document(userID)
                .collection("watchlist")
                .get()
                .await()

            for (document in result) {
                Log.d(TAG, "${document.id} => ${document.data}")
                watchlistMovies += WatchlistMovie.fromMap(document.data)
            }
        }
        Log.d(TAG, "getWatchlistMovies: $watchlistMovies")
        return@withContext watchlistMovies
    }

    suspend fun removeMovieFromWatchlist(movieID: Long) = withContext(Dispatchers.IO) {
        getCurrentUserID()?.let { userID ->
            database.collection("users")
                .document(userID)
                .collection("watchlist")
                .document(movieID.toString())
                .delete()
                .await()
        }
    }

    fun updateRecommendDatabase(watchlistMovieMap: Map<String, Any?>) {
        getCurrentUserID()?.let { database.collection("users")
            .document(it)
            .collection("recommend")
            .document(watchlistMovieMap["movieID"]
                .toString())
            .set(watchlistMovieMap) }
    }

    suspend fun getRecommenedMovies(): List<Recommend> = withContext(Dispatchers.IO) {
        val recommenedMovies = mutableListOf<Recommend>()
        getCurrentUserID()?.let { userID ->
            val result = database.collection("users")
                .document(userID)
                .collection("recommend")
                .get()
                .await()

            for (document in result) {
                Log.d(TAG, "${document.id} => ${document.data}")
                recommenedMovies += Recommend.fromMap(document.data)
            }
        }
        return@withContext recommenedMovies
    }
}