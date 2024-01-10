package com.example.mediaapp.models

data class CurrentUser(
    val user: User,
    val watchlist : List<WatchlistMovie>,
) {
    companion object {
        fun fromMap(map: Map<String, Any?>): CurrentUser {
            return CurrentUser(
                User.fromMap(map["user"] as? Map<String, Any?> ?: mapOf()),
                map["watchlist"] as? List<WatchlistMovie> ?: listOf()
            )
        }
    }
}
