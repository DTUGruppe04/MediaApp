package com.example.mediaapp.models

data class Stats(
    val watched: Long,
    val reviews: Long,
    val rated: Long,
    val recommends: Long,
    val saved: Long
) {
    companion object {
        fun fromMap(map: Map<String, Any?>): Stats {
            return Stats(
                map["watched"] as? Long ?: 0,
                map["reviews"] as? Long ?: 0,
                map["rated"] as? Long ?: 0,
                map["recommends"] as? Long ?: 0,
                map["saved"] as? Long ?: 0
            )
        }

        fun toMap(stats: Stats): Map<String, Any?> {
            return mapOf(
                "watched" to stats.watched,
                "reviews" to stats.reviews,
                "rated" to stats.rated,
                "recommends" to stats.recommends,
                "saved" to stats.saved
            )
        }
    }
}
