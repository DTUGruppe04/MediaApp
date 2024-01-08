package com.example.mediaapp.models

data class RatedMovie(
    val movieID: Long,
    val amountOfRatings: Long,
    val averageRating: Int
) {
    companion object {
        fun fromMap(map: Map<String, Any?>): RatedMovie {
            return RatedMovie(
                map["movieID"] as? Long ?: -1,
                map["amountOfRatings"] as? Long ?: 0,
                map["averageRating"] as? Int ?: 0
            )
        }
    }
}
