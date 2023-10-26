package com.example.mediaapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController
import com.example.mediaapp.ui.nav.BottomNavBar
import com.example.mediaapp.ui.nav.NavigationGraph

data class BottomNavItem(
    val name: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) },
    ) { innerPadding ->
        NavigationGraph(navController = navController)
        Modifier.padding(innerPadding)
    }
}

/*
@Preview
@Composable
fun PreviewMainScreen() {
    val navController = rememberNavController()
    BottomNavigationBar(navController = navController)
    NavBottomGraph(navController = navController)
}
*/