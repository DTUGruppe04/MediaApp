package com.example.mediaapp.models

data class WatchlistMovie(
    val movieID: Long,
    val posterPath: String,
    val title: String,
    val genres: List<Int>,
    val description: String
) {
    companion object {
        fun fromMap(map: Map<String, Any?>): WatchlistMovie {
            return WatchlistMovie(
                map["movieID"] as? Long ?: -1,
                map["posterPath"] as? String ?: "",
                map["title"] as? String ?: "",
                map["genres"] as? List<Int>?: listOf(),
                map["description"] as? String ?: ""
            )
        }
    }
}
