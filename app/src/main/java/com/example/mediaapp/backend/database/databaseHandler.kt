package com.example.mediaapp.backend.database

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mediaapp.models.RatingForDatabase
import com.example.mediaapp.models.Recommend
import com.example.mediaapp.models.WatchlistMovie
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DatabaseHandler private constructor() {

    private val database = Firebase.firestore
    private val userCache = mutableMapOf<String, Map<String, Any?>>()
    private var watchListCache: List<WatchlistMovie>? = null
    private var recommendCache: List<Recommend>? = null

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

    private fun getCurrentUserID(): String? {
        return Firebase.auth.currentUser?.uid
    }

    suspend fun updateWatchlistMovie(watchlistMovieMap: Map<String, Any?>) {
        getCurrentUserID()?.let { database.collection("users")
            .document(it)
            .collection("watchlist")
            .document(watchlistMovieMap["movieID"]
                .toString())
            .set(watchlistMovieMap) }

        watchListCache = null
        removeMovieRecommend(watchlistMovieMap["movieID"].toString().toLong())
    }

    suspend fun getWatchlistMovies(): List<WatchlistMovie> {
        // If the cache is not null and not empty, return it instead of making a database call
        if (watchListCache != null && watchListCache!!.isNotEmpty()) {
            Log.d(TAG, "Returning cached watchlist movies")
            return watchListCache!!
        }

        val watchlistMovies = mutableListOf<WatchlistMovie>()
        val result = database.collection("users")
            .document(getCurrentUserID()!!)
            .collection("watchlist")
            .get()
            .await()

        for (document in result) {
            Log.d(TAG, "${document.id} => ${document.data}")
            watchlistMovies += WatchlistMovie.fromMap(document.data)
        }

        Log.d(TAG, "getWatchlistMovies: $watchlistMovies")
        watchListCache = watchlistMovies

        return watchlistMovies
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

        watchListCache = null
    }

    suspend fun updateRatedMoviesUser(movieID: String, ratedMovieMap: RatingForDatabase) {
        database.collection("users")
            .document(getCurrentUserID()!!)
            .collection("ratedMovies")
            .document(movieID)
            .set(ratedMovieMap)
            .await()
    }

    suspend fun updateRatedMovies(movieID: String, ratedMovieMap: RatingForDatabase) {
        database.collection("ratedMovies")
            .document(movieID)
            .collection("ratings")
            .document(getCurrentUserID()!!)
            .set(ratedMovieMap)
            .await()
    }

    suspend fun getRatedMovie(movieID: String): List<RatingForDatabase> {
        val ratings = mutableListOf<RatingForDatabase>()
        val result = database.collection("ratedMovies")
            .document(movieID)
            .collection("ratings")
            .get()
            .await()

        for (document in result) {
            Log.d(TAG, "${document.id} => ${document.data}")
            ratings += RatingForDatabase.fromMap(document.data)
        }
        Log.d(TAG, "getRatedMovie: $ratings")
        return ratings
    }


    fun updateRecommendDatabase(watchlistMovieMap: Map<String, Any?>) {
        getCurrentUserID()?.let { database.collection("users")
            .document(it)
            .collection("recommend")
            .document(watchlistMovieMap["movieID"]
                .toString())
            .set(watchlistMovieMap) }

        recommendCache = null
    }

    suspend fun getRecommendMovies(): List<Recommend> = withContext(Dispatchers.IO) {

        if (recommendCache != null && recommendCache!!.isNotEmpty()) {
            Log.d(TAG, "Returning cached recommend movies")
            return@withContext recommendCache!!
        }

        val recommendMovies = mutableListOf<Recommend>()
        getCurrentUserID()?.let { userID ->
            val result = database.collection("users")
                .document(userID)
                .collection("recommend")
                .get()
                .await()

            for (document in result) {
                Log.d(TAG, "${document.id} => ${document.data}")
                recommendMovies += Recommend.fromMap(document.data)
            }
        }
        recommendCache = recommendMovies
        return@withContext recommendMovies
    }

    suspend fun removeMovieRecommend(movieID: Long) = withContext(Dispatchers.IO) {
        getCurrentUserID()?.let { userID ->
            database.collection("users")
                .document(userID)
                .collection("recommend")
                .document(movieID.toString())
                .delete()
                .await()
        }

        recommendCache = null
    }
    companion object {
        @Volatile
        private var INSTANCE: DatabaseHandler? = null

        fun getInstance(): DatabaseHandler {
            return INSTANCE ?: synchronized(this) {
                val instance = DatabaseHandler()
                INSTANCE = instance
                instance
            }
        }
    }
}