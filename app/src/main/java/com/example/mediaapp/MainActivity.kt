package com.example.mediaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediaapp.ui.theme.MediaAppTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaAppTheme {
                LoginPageLayout()
            }
        }
    }
}
//0xFF2E2E2E

@Composable
fun LoginPageLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.login_background_color))
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
            contentAlignment = Alignment.Center) {
            Text(stringResource(R.string.login_top_name),
                fontSize = 22.sp,
                lineHeight = 28.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        Text(stringResource(R.string.login),
            fontSize = 30.sp,
            lineHeight = 28.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 29.dp, top = 45.dp))
        Text(stringResource(R.string.please),
            fontSize = 12.sp,
            lineHeight = 28.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 30.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    MediaAppTheme {
        LoginPageLayout()
    }
}