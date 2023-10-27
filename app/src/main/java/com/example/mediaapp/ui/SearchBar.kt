package com.example.mediaapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Row(
        modifier = Modifier
            .padding(4.dp)
            .width(360.dp)
            .height(56.dp)
            .background(
                color = Color(0xFF2B2930),
                shape = RoundedCornerShape(size = 28.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        TextField(
            value = text,
            onValueChange = {text = it},
            placeholder = {
                Text(
                    text = "Search",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFCAC4D0),
                        letterSpacing = 0.5.sp
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
            ),
            modifier = Modifier.weight(1f),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Box(modifier = Modifier.padding(8.dp)){
            Icon(
                Icons.Filled.Search,
                tint = Color(0xFFCAC4D0),
                contentDescription = null)
        }
    }
}

@Preview
@Composable
fun SearchBarPreview(){
    Column {
        SearchBar()

        // Rest of your screen content...
    }
}