package com.example.mediaapp.backend.database

import android.content.ContentValues.TAG
import android.util.Log
import com.example.mediaapp.backend.RecommendationEngine
import com.example.mediaapp.models.CurrentUser
import com.example.mediaapp.models.RatingForDatabase
import com.example.mediaapp.models.Recommend
import com.example.mediaapp.models.User
import com.example.mediaapp.models.WatchlistMovie
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class DatabaseHandler private constructor() {

    private val database = Firebase.firestore
    private var userCache: CurrentUser? = null
    private var watchListCache: List<WatchlistMovie>? = null
    private var watchedListCache: List<WatchlistMovie>? = null
    private var recommendCache: List<Recommend>? = null

    suspend fun getUserFromDatabase(): CurrentUser {
        // Check if the user data is in the cache
        if (userCache != null) {
            Log.d(TAG, "Returning cached user data")
            return userCache!!
        }

        // If not in the cache, get it from the database

        val userData = database.collection("users").document(getCurrentUserID()!!).get().await()
        Log.d(TAG, userData.data.toString())
        val user = User.fromMap(userData.data!!)
        val currentUser = CurrentUser(user, getWatchlistMovies())
        Log.d(TAG, currentUser.toString())
        // Store the user data in the cache for future use
        userCache = currentUser

        return currentUser
    }

    fun updateUserInDatabase(userId: String, userMap: Map<String, Any?>) {
        // Update the user data in the database
        database.collection("users").document(userId).set(userMap)

        // Also update the user data in the cache
        userCache = null
    }

    private fun getCurrentUserID(): String? {
        return Firebase.auth.currentUser?.uid
    }

    fun updateWatchedMovie(watchedMovieMap: Map<String, Any?>) {
        getCurrentUserID()?.let { database.collection("users")
            .document(it)
            .collection("watched")
            .document(watchedMovieMap["movieID"]
                .toString())
            .set(watchedMovieMap) }

        watchListCache = null
        watchedListCache = null
    }

    suspend fun getWatchedMovies(): List<WatchlistMovie> {
        // If the cache is not null and not empty, return it instead of making a database call
        if (watchedListCache != null) {
            Log.d(TAG, "Returning cached watched movies")
            return watchedListCache!!
        }

        val watchedMovies = mutableListOf<WatchlistMovie>()
        val result = database.collection("users")
            .document(getCurrentUserID()!!)
            .collection("watched")
            .get()
            .await()

        for (document in result) {
            Log.d(TAG, "${document.id} => ${document.data}")
            watchedMovies += WatchlistMovie.fromMap(document.data)
        }

        Log.d(TAG, "getWatchedMovies: $watchedMovies")
        watchedListCache = watchedMovies

        return watchedMovies
    }

    fun removeMovieFromWatched(movieID: Long) {
        getCurrentUserID()?.let { database.collection("users")
            .document(it)
            .collection("watched")
            .document(movieID.toString())
            .delete() }

        watchListCache = null
        watchedListCache = null
    }

    suspend fun updateWatchlistMovie(watchlistMovieMap: Map<String, Any?>) {
        getCurrentUserID()?.let { database.collection("users")
            .document(it)
            .collection("watchlist")
            .document(watchlistMovieMap["movieID"]
                .toString())
            .set(watchlistMovieMap) }

        watchedListCache = null
        watchListCache = null
        removeMovieRecommend(watchlistMovieMap["movieID"].toString().toLong())
    }

    suspend fun getWatchlistMovies(): List<WatchlistMovie> {
        // If the cache is not null and not empty, return it instead of making a database call
        if (watchListCache != null) {
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

        watchedListCache = null
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

    suspend fun getUserRatedMovie(): List<RatingForDatabase> {
        val ratings = mutableListOf<RatingForDatabase>()
        val result = getCurrentUserID()?.let {
            database.collection("users")
                .document(it)
                .collection("ratedMovies")
                .get()
                .await()
        }

        if (result != null) {
            for (document in result) {
                Log.d(TAG, "${document.id} => ${document.data}")
                ratings += RatingForDatabase.fromMap(document.data)
            }
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