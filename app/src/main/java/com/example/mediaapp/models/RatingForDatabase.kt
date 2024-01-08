package com.example.mediaapp.models

data class RatingForDatabase(
    val movieID: Long,
    val rating: Int
) {
    companion object {
        fun fromMap(map: Map<String, Any?>): RatingForDatabase {
            return RatingForDatabase(
                map["movieID"] as? Long ?: -1,
                map["rating"] as? Int ?: -1,
            )
        }
    }
}
