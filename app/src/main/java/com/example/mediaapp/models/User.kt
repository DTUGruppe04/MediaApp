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
)
