package com.example.mediaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.Recommend
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieDetailPage()
        }
    }
}
//0xFF2E2E2E
@Preview
@Composable
fun MovieDetailPage() {

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF2E2E2E))) {
        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)) {
                Image(painter = painterResource(R.drawable.oppenheimer),
                    contentDescription = "Oppenheimer",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop)
                Box(modifier = Modifier
                    .padding(20.dp)
                    .height(20.dp)
                    .width(90.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color(0xff4a4458))
                    ) {
                        Icon(imageVector = Icons.Outlined.VisibilityOff,
                            contentDescription = "N/A")
                        Text(text = "Not Watched",
                            color = Color.White,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .align(
                                    Alignment.CenterEnd
                                )
                                .padding(end = 5.dp))
                    }
                Box(modifier = Modifier
                    .padding(start = 280.dp)
                    .padding(top = 20.dp)
                    .height(20.dp)
                    .width(90.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color(0xff4a4458))
                ) {
                    Icon(imageVector = Icons.Outlined.Recommend,
                        contentDescription = "N/A")
                    Text(text = "Not Watched",
                        color = Color.White,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .align(
                                Alignment.CenterEnd
                            )
                            .padding(end = 5.dp))
                }
            }
        }
        item {
            //Top part
            MovieDescription("The story of American scientist, J Robert Oppenheimer, and his role in the development of the atomic bomb", "Oppenheimer", "8,7/10", "2023")
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
        Row(modifier = Modifier
            .padding(start = 130.dp)
            .padding(top = 60.dp)
            .height(20.dp)
            .fillMaxWidth()) {
            TagBox(shape = RoundedCornerShape(10.dp), "Drama")
            Spacer(modifier = Modifier.padding(3.dp))
            TagBox(shape = RoundedCornerShape(10.dp), "Bibliography")
            Spacer(modifier = Modifier.padding(3.dp))
            TagBox(shape = RoundedCornerShape(10.dp), "History")
        }
        Box(modifier = Modifier
            .padding(start = 130.dp)
            .padding(top = 90.dp)
            .height(100.dp)
            .fillMaxWidth()) {
            Text(text = description, color = Color.White)
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
                fontSize = 10.sp)
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




















