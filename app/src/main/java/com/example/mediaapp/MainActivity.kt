@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mediaapp
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.ui.nav.NavigationGraphLogin
import com.example.mediaapp.ui.theme.MediaAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scope = rememberCoroutineScope()
                    val navController = rememberNavController()
                    NavigationGraphLogin(navController = navController)
                    scope.launch {
                        val popularMovies = APIHandler().getPopularMovieData("day")
                        if (popularMovies != null) {
                            Log.d("API", popularMovies.results[1].title)
                            Log.d("API", popularMovies.results[1].genre_ids[0].toString())
                            Log.d("API", popularMovies.results[1].poster_path)
                        } else {
                            Log.d("API", "Returned Null")
                        }
                    }

                }
            }
        }
    }
}