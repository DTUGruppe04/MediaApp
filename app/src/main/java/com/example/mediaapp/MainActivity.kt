@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mediaapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.outlined.FormatListBulleted
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediaapp.ui.theme.MediaAppTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomNavigationBar()
                }
            }
        }
    }
}

data class BottomNavItem(
    val name: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

//Using SuppressLint in order to make it stop saying it need data when it does not
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationBar(modifier : Modifier = Modifier) {
    val containerColor = Color(android.graphics.Color.parseColor("#211F26"))
    val contentColor = Color(android.graphics.Color.parseColor("#FFFFFF"))
    val indicatorColor = Color(android.graphics.Color.parseColor("#4a4458"))

    val bottomNavItems = listOf(
        BottomNavItem(
            name = "Home",
            route = "home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavItem(
            name = "Following",
            route = "following",
            selectedIcon = Icons.Filled.Subscriptions,
            unselectedIcon = Icons.Outlined.Subscriptions
        ),
        BottomNavItem(
            name = "Watchlist",
            route = "watchlist",
            selectedIcon = Icons.Filled.FormatListBulleted,
            unselectedIcon = Icons.Outlined.FormatListBulleted
        ),
        BottomNavItem(
            name = "Search",
            route = "search",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search
        ),
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = containerColor,
                tonalElevation = 0.dp
            ) {
                bottomNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = contentColor,
                            unselectedIconColor = contentColor,
                            indicatorColor = indicatorColor
                        ),
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            //navController.navigate(item.route)
                        },
                        label = {
                            Text(text = item.name, color = contentColor)
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.name
                            )
                        }
                    )
                }
            }
        }
    ) {

    }
}

/*
//Old Code
@Composable
fun ButtomNavigationBar(modifier : Modifier = Modifier) {
    val items = listOf("Home", "Following", "Watchlist", "Search")
    val containerColor = Color(android.graphics.Color.parseColor("#211F26"))
    val contentColor = Color(android.graphics.Color.parseColor("#FFFFFF"))
    NavigationBar (
        containerColor = containerColor,
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Home Page") },
            label = { Text(items[0], color = contentColor ) },
            selected = false,
            onClick = { null },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = contentColor,
                unselectedIconColor = contentColor
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Subscriptions, contentDescription = "Following Page") },
            label = { Text(items[1], color = contentColor) },
            selected = false,
            onClick = { null },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = contentColor,
                unselectedIconColor = contentColor
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.FormatListBulleted, contentDescription = "Watchlist Page") },
            label = { Text(items[2], color = contentColor) },
            selected = false,
            onClick = { null },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = contentColor,
                unselectedIconColor = contentColor
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Search, contentDescription = "Search Page") },
            label = { Text(items[3], color = contentColor) },
            selected = false,
            onClick = { null },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = contentColor,
                unselectedIconColor = contentColor
            )
        )
    }
}
*/

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MediaAppTheme {
        BottomNavigationBar()
    }
}