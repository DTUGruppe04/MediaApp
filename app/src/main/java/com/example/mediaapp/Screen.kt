package com.example.mediaapp

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Following : Screen("following")
    object Watchlist : Screen("watchlist")
    object Search : Screen("search")
}
