package com.example.mediaapp.ui.nav

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mediaapp.MainActivity
import com.example.mediaapp.MainScreen
import com.example.mediaapp.Screen
import com.example.mediaapp.screens.HomeScreen
import com.example.mediaapp.screens.FollowingListPage
import com.example.mediaapp.screens.LoginPageLayout
import com.example.mediaapp.screens.MainPageLayout
import com.example.mediaapp.screens.MoviePageScreen
import com.example.mediaapp.screens.WatchlistPage
import com.example.mediaapp.screens.SearchScreen
import com.example.mediaapp.screens.ProfilePageLayout
import com.example.mediaapp.screens.SettingpageLayout
import com.example.mediaapp.screens.YoufollowPageLayout
import com.example.mediaapp.screens.YourfollowersPageLayout

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationLoginGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen()
        }
        composable(route = Screen.Register.route) {

        }
    }
}

 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationGraph(navController: NavHostController, drawerState: DrawerState) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, drawerState = drawerState)
        }
        composable(route = Screen.Following.route) {
            FollowingListPage(navController = navController, drawerState = drawerState)
        }
        composable(route = Screen.Watchlist.route) {
            WatchlistPage(navController = navController, drawerState = drawerState)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(route = Screen.Profile.route) {
            ProfilePageLayout(navController = navController, drawerState = drawerState)
        }
        composable(route = Screen.YouFollow.route) {
            YoufollowPageLayout(navController = navController, drawerState = drawerState)
        }
        composable(route = Screen.YourFollowers.route) {
            YourfollowersPageLayout(navController = navController, drawerState = drawerState)
        }
        composable(route = Screen.Settings.route) {
            SettingpageLayout(navController = navController, drawerState = drawerState)
        }
        composable(route = Screen.MoviePage.route) {
            MoviePageScreen(navController = navController)
        }
    }
}