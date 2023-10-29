package com.example.mediaapp

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Following : Screen("following")
    object Watchlist : Screen("watchlist")
    object Search : Screen("search")
    object Profile : Screen("profile")
    object YouFollow : Screen("you follow")
    object YourFollowers : Screen("your followers")
    object Settings : Screen("settings")
    object MoviePage : Screen("movie page")
    object Login : Screen("login")
    object Register : Screen("register")
    object ForgotPassword : Screen("forgot password")
    object MainScreen : Screen("main screen")
}
