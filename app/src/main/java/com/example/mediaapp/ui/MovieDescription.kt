package com.example.mediaapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediaapp.R

@Composable
fun MovieDescription(description: String, title: String, rating : String, year : String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF3F3F3F))
            .height(250.dp)
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.oppenheimerposter
            ),
            contentDescription = "Oppenheimer poster",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .padding(top = 10.dp)
                .padding(bottom = 50.dp)
        )
        Text(
            text = title,
            modifier = Modifier
                .padding(start = 130.dp)
                .padding(top = 10.dp),
            color = Color.White,
            fontSize = 30.sp
        )
        LazyRow(
            modifier = Modifier
                .padding(start = 130.dp)
                .padding(top = 60.dp)
                .height(20.dp)
                .fillMaxWidth()
        ) {
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
        Box(
            modifier = Modifier
                .padding(start = 130.dp)
                .padding(top = 90.dp)
                .height(100.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = description,
                color = Color.White,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 130.dp)
                .padding(top = 180.dp)
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(60.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color(0xff4a4458))
            ) {
                Text(
                    text = "Rate this",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "star",
                    tint = Color.Yellow,
                    modifier = Modifier
                        .fillMaxHeight()
                )
            }
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = rating,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxHeight()
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .fillMaxHeight()
            ) {
                Icon(
                    imageVector = Icons.Outlined.BookmarkAdd,
                    contentDescription = "Bookmark",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 210.dp)
                .padding(start = 45.dp)
        ) {
            Text(text = year, color = Color.White)
        }
    }
}