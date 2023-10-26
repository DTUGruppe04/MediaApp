package com.example.mediaapp.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mediaapp.Screen
import com.example.mediaapp.screens.HomeScreen
import com.example.mediaapp.screens.FollowingScreen
import com.example.mediaapp.screens.WatchlistScreen
import com.example.mediaapp.screens.SearchScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Following.route) {
            FollowingScreen(navController = navController)
        }
        composable(route = Screen.Watchlist.route) {
            WatchlistScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
    }
}
