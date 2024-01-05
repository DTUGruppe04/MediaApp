package com.example.mediaapp.models

data class Genre(
    val id: Int,
    val name: String
) {
    companion object {
        fun fromMap(map: Map<String, Any?>): Genre {
            return Genre(
                map["id"] as? Int ?: -1,
                map["name"] as? String ?: ""
            )
        }
    }
}