package com.example.mediaapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediaapp.BottomNavigationBar

@Composable
fun Search(message : String, modifier : Modifier = Modifier ) {
    Text(
        text = message,
        fontSize = 30.sp,
        textAlign = TextAlign.Center,
        color = Color.Black,
        modifier = Modifier
            .padding(16.dp)
    )
}

@Preview
@Composable
fun SearchPreview() {
    BottomNavigationBar()
    Search("Search Page")
}