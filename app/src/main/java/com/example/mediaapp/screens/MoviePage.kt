package com.example.mediaapp.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.Recommend
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediaapp.R
import com.example.mediaapp.ui.nav.TopNavBarD
import com.example.mediaapp.ui.theme.MediaAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailPage(navController: NavController, drawerState: DrawerState) {
    MediaAppTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
                .background(color = Color(0xFF2E2E2E))
        ) {
            item {
                TopNavBarD(navController = navController, drawerState = drawerState)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.oppenheimer2),
                        contentDescription = "Oppenheimer",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .height(32.dp)
                                .width(132.dp)
                                .wrapContentWidth()
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(color = Color(0xFF1D1B20))
                        ) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.VisibilityOff,
                                        contentDescription = "N/A",
                                        tint = colorResource(R.color.white),
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .size(18.dp)
                                    )
                                    Text(
                                        text = "Not Watched",
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        modifier = Modifier
                                            .padding(start = 8.dp, end = 8.dp)
                                    )
                                }

                            }
                        }
                        Box(
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .height(32.dp)
                                .width(126.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(color = Color(0xff4a4458))
                        ) {
                            IconButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Recommend,
                                        contentDescription = "N/A",
                                        tint = colorResource(R.color.white),
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .size(18.dp)
                                    )
                                    Text(
                                        text = "Recommend",
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        modifier = Modifier
                                            .padding(start = 8.dp, end = 8.dp)
                                    )
                                }

                            }

                        }
                    }
                }

            }
            item {
                //Top part
                MovieDescription(
                    "The story of American scientist, J Robert Oppenheimer, and his role in the development of the atomic bomb",
                    "Oppenheimer",
                    "8,7/10",
                    "2023"
                )
            }
            item {
                Detail(detail = "Director", info = "Christopher Nolan")
            }
            item {
                Detail(detail = "Director", info = "Christopher Nolan")
            }
            item {
                Detail(detail = "Director", info = "Christopher Nolan")
            }
            item {
                Detail(detail = "Director", info = "Christopher Nolan")
            }
            item {
                Detail(detail = "Director", info = "Christopher Nolan")
            }
            item {
                Detail(detail = "Director", info = "Christopher Nolan")
            }
        }
    }
}

//060404
@Composable
fun MovieDescription(description: String, title: String, rating : String, year : String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color(0xFF3F3F3F))
        .height(250.dp)) {
        Image(painter = painterResource(
            id = R.drawable.oppenheimerposter),
            contentDescription = "Oppenheimer poster",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .padding(top = 10.dp)
                .padding(bottom = 50.dp))
        Text(text = title,
            modifier = Modifier
                .padding(start = 130.dp)
                .padding(top = 10.dp),
            color = Color.White,
            fontSize = 30.sp)
        LazyRow(modifier = Modifier
            .padding(start = 130.dp)
            .padding(top = 60.dp)
            .height(20.dp)
            .fillMaxWidth()) {
            item {
                TagBox(shape = RoundedCornerShape(10.dp), "Drama")
                Spacer(modifier = Modifier.padding(3.dp))
            }
            item {
                TagBox(shape = RoundedCornerShape(10.dp), "Bibliography")
                Spacer(modifier = Modifier.padding(3.dp))
            }
            item {
                TagBox(shape = RoundedCornerShape(10.dp), "History")
            }
        }
        Box(modifier = Modifier
            .padding(start = 130.dp)
            .padding(top = 90.dp)
            .height(100.dp)
            .fillMaxWidth()) {
            Text(
                text = description,
                color = Color.White,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )
        }
        Row(modifier = Modifier
            .padding(start = 130.dp)
            .padding(top = 180.dp)
            .fillMaxWidth()
            .height(40.dp)) {
            Box(modifier = Modifier
                .fillMaxHeight()
                .width(60.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color(0xff4a4458))
            ) {
                Text(text = "Rate this",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Box(modifier = Modifier
                .fillMaxHeight()) {
                Icon(imageVector = Icons.Filled.Star,
                    contentDescription = "star",
                    tint = Color.Yellow,
                    modifier = Modifier
                        .fillMaxHeight())
            }
            Box(modifier = Modifier
                .padding(10.dp)
                .fillMaxHeight()) {
                Text(text = rating,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxHeight())
            }
            Box(modifier = Modifier
                .padding(start = 15.dp)
                .fillMaxHeight()) {
                Icon(imageVector = Icons.Outlined.BookmarkAdd,
                    contentDescription = "Bookmark",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp))
            }
        }
        Box(modifier = Modifier
            .padding(top = 210.dp)
            .padding(start = 45.dp)
        ) {
            Text(text = year, color = Color.White)
        }
    }
}

//can be made more customizable by making the textlength decide the length of the box
@Composable
fun TagBox(shape : Shape, tag : String) {
    Column(modifier = Modifier
        .height(15.dp)
        .width(80.dp)) {
        Box(modifier = Modifier
            .fillMaxSize()
            .clip(shape)
            .background(color = Color(0xFF282727))) {
            Text(text = tag,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 12.sp)
        }
    }
}

@Composable
fun Detail(detail : String, info : String) {
    Box(modifier = Modifier
        .padding(top = 20.dp)
        .padding(start = 20.dp)) {
        Text(text = detail,
            fontWeight = FontWeight.Bold,
            color = Color.White)
    }
    Box(modifier = Modifier
        .padding(top = 5.dp)
        .padding(start = 20.dp)) {
        Text(text = info,
            color = Color.White)
    }
}
