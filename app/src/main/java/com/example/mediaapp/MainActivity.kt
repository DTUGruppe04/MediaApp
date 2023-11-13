@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mediaapp
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.apirequests.RetrofitInstance
import com.example.mediaapp.apirequests.popularMovie
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
                    /*
                    val json = JSONObject("""{
                        "name": "John",
                        "age": 30,
                        "city": "New York"
                    }""")
                    */
                    val response = try {
                        RetrofitInstance.api.getPopularMovie("week")
                    } catch (e) {
                        Log.d("API", e)
                    }

                    if(res)

                    Log.d("API", )
                    //val navController = rememberNavController()
                    //NavigationGraphLogin(navController = navController)
                }
            }
        }
    }
}