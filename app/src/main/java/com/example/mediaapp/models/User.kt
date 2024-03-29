package com.example.mediaapp.models

data class User(
    val username: String,
    val name: String,
    val location: String,
    val followers: List<String>,
    val following: List<String>,
    val description: String,
    val profilePicture: String,
    val stats: Stats,
    val favorites: List<String>,
    val recentlyWatched: List<String>,
    val ratedMovies: List<String>
) {
    companion object {
        fun fromMap(map: Map<String, Any?>): User {
            return User(
                map["username"] as? String ?: "",
                map["name"] as? String ?: "",
                map["location"] as? String ?: "",
                map["followers"] as? List<String> ?: listOf(),
                map["following"] as? List<String> ?: listOf(),
                map["description"] as? String ?: "",
                map["profilePicture"] as? String ?: "",
                Stats.fromMap(map["stats"] as? Map<String, Any?> ?: mapOf()),
                map["favorites"] as? List<String> ?: listOf(),
                map["recentlyWatched"] as? List<String> ?: listOf(),
                map["ratedMovies"] as? List<String> ?: listOf()
            )
        }

        fun toMap(user: User): Map<String, Any?> {
            return mapOf(
                "username" to user.username,
                "name" to user.name,
                "location" to user.location,
                "followers" to user.followers,
                "following" to user.following,
                "description" to user.description,
                "profilePicture" to user.profilePicture,
                "stats" to Stats.toMap(user.stats),
                "favorites" to user.favorites,
                "recentlyWatched" to user.recentlyWatched,
                "ratedMovies" to user.ratedMovies
            )
        }
    }
}


