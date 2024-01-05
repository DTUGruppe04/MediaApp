package com.example.mediaapp.models

data class CurrentUser(
    val user: User,
    val watchlist : List<String>,
)
