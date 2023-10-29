package com.example.mediaapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var isFocused by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(56.dp)
            .background(
                shape = RoundedCornerShape(size = 28.dp),
                color = Color(0xFF2B2930)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        if (isFocused) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back arrow",
                tint = Color(0xFFCAC4D0),
                modifier = Modifier
                    .clickable {}
                    .padding(start = 16.dp)
            )
        }
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
                    modifier = Modifier
                        .fillMaxWidth()
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color(0xFFCAC4D0),
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
        )
        if (isFocused) {
            Box(modifier = Modifier.padding(end = 24.dp)){
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear icon",
                    tint = Color(0xFFCAC4D0),
                    modifier = Modifier.clickable { text = TextFieldValue("") }  // clear the text when clicked
                )
            }
        } else {
            Box(modifier = Modifier.padding(end = 24.dp))
            {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search icon",
                    tint = Color(0xFFCAC4D0)
                )
            }
        }
    }
}
