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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediaapp.ui.theme.MediaAppTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaAppTheme {
                MainPageLayout()
            }
        }
    }
}
//0xFF2E2E2E

@Composable
fun MainPageLayout() {
    LazyColumn(modifier = Modifier.fillMaxSize()){
        item {
            Box() {
                Column(modifier = Modifier
                    .fillMaxWidth()) {
                    Image(painter = painterResource(R.drawable.barbie),
                        contentDescription = "barbie",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop)
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(color = Color(0xFF2E2E2E))) {
                        Row {
                            Column {
                                Text(stringResource(R.string.main_page_text1), color = Color.White, fontSize = 16.sp, modifier = Modifier.padding(start = 120.dp, top = 5.dp))
                                Text(stringResource(R.string.main_page_text2), color = Color.White, fontSize = 10.sp, modifier = Modifier.padding(start = 120.dp))
                            }
                            Spacer(Modifier.size(75.dp))
                            Image(painter = painterResource(R.drawable.bookmark),
                                contentDescription = "bookmark",
                                modifier = Modifier
                                    .padding(5.dp)
                                    .size(33.dp))
                        }
                    }
                }
                Image(painter = painterResource(R.drawable.barbie_icon),
                    contentDescription = "barbie_icon",
                    modifier = Modifier
                        .padding(start = 10.dp, top = 150.dp)
                        .size(121.dp))
                Image(painter = painterResource(R.drawable.settings_icon), contentDescription = "settings", modifier = Modifier
                    .padding(10.dp)
                    .size(24.dp))
                Image(painter = painterResource(R.drawable.arrow_left), contentDescription = "arrow_left", modifier = Modifier
                    .padding(start = 10.dp, top = 210.dp)
                    .size(16.dp))
                Image(painter = painterResource(R.drawable.arrow_right), contentDescription = "arrow_right", modifier = Modifier
                    .padding(start = 365.dp, top = 210.dp)
                    .size(16.dp))
            }
        }
        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(color = Color(0xFF3F3F3F))) {
                Column {
                    Text(stringResource(R.string.main_page_trending), color = Color.White, fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp, top = 5.dp))
                    LazyRow(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        item {
                            Box(modifier = Modifier
                                .width(90.dp)
                                .height(180.dp)) {
                                Column(modifier = Modifier.fillMaxSize()) {
                                    Image(painter = painterResource(R.drawable.first_movie), contentDescription = "first_movie", modifier = Modifier
                                        .padding(start = 10.dp)
                                        .width(90.dp)
                                        .height(139.dp))
                                    Text(stringResource(R.string.main_page_first_movie), color = Color.White, fontSize = 12.sp, modifier = Modifier.padding(start = 10.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    MediaAppTheme {
        MainPageLayout()
    }
}