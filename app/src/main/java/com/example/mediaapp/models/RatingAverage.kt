package com.example.mediaapp.models

data class RatingAverage(
    val movieID: Long,
    val average: Double,
    val amount: Int
) {
    companion object {
        fun fromMap(map: Map<String, Any?>): RatingAverage {
            return RatingAverage(
                map["movieID"] as? Long ?: -1,
                map["average"] as? Double ?: -1.0,
                map["amount"] as? Int ?: -1
            )
        }
    }
}
