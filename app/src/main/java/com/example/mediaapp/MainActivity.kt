package com.example.mediaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mediaapp.ui.theme.MediaAppTheme
import com.example.mediaapp.screens.FollowingListPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaAppTheme {
                FollowingListPage()
                }
            }
        }
    }