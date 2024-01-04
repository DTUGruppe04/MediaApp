package com.example.mediaapp.models
data class Recommend(
    val movieID: Long,
    val posterPath: String,
    val title: String
) {
    companion object {
        fun fromMap(map: Map<String, Any?>): Recommend {
            return Recommend(
                map["movieID"] as? Long ?: -1,
                map["posterPath"] as? String ?: "",
                map["title"] as? String ?: ""
            )
        }
    }
}
