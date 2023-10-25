@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mediaapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediaapp.ui.theme.MediaAppTheme


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
                    MainScreen()
                }
            }
        }
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
        MainScreen()
    }
}