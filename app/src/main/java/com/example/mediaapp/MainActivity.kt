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
                        //val movie = APIHandler().getPopularMovie("day")
                        //val movie = APIHandler().searchForMovie("Bluebittle")
                        //val movie = APIHandler().getMovieDetail("872585")
                        //val movie = APIHandler().getSimilarMovies("872585")
                        val movie = APIHandler().getMovieReviews("872585")
                        if (movie != null) {
                            Log.d("API", movie.results[0].author)
                            Log.d("API", movie.results[0].content)
                        } else {
                            Log.d("API", "Returned Null")
                        }
                    }

                }
            }
        }
    }
}