@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mediaapp
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mediaapp.apirequests.APIHandler
import com.example.mediaapp.ui.nav.NavigationGraphLogin
import com.example.mediaapp.ui.theme.MediaAppTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    companion object {
        private val key = "text"
        fun create(context: Context, text: String? = null): Intent =
            Intent(context, MainActivity::class.java).putExtra(
                key, text
            )
    }
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
                    val navController = rememberNavController()
                    NavigationGraphLogin(navController = navController)
                }
            }
        }
    }

}