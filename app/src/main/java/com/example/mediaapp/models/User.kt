package com.example.mediaapp.models

data class User(
    val username: String,
    val name: String,
    val location: String,
    val followers: List<User>,
    val following: List<User>,
    val description: String,
    val profilePicture: String,
    val stats: Stats,
    val favorites: List<Int>,
    val recentlyWatched: List<Int>,
)
