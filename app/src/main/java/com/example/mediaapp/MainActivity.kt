package com.example.mediaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieDescription("long movie description", "Oppenheimer")
        }
    }
}
//0xFF2E2E2E

@Composable
fun MovieDetailPage() {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF2E2E2E))) {
        item {
            //Top part
            Image(painter = painterResource(R.drawable.oppenheimer),
                contentDescription = "Oppenheimer",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop)
        }
    }
}
//060404
@Composable
fun MovieDescription(description: String, title: String) {
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
            .width(80.dp)) {
            TagBox(shape = RoundedCornerShape(10.dp), "Drama")
            TagBox(shape = RoundedCornerShape(10.dp), "Bibliography")
            TagBox(shape = RoundedCornerShape(10.dp), "Drama")
        }
    }
}

//can be made more customizable by making the textlength decide the length of the box
@Composable
fun TagBox(shape : Shape, tag : String) {
    Column(modifier = Modifier
        .fillMaxWidth()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .clip(shape)
            .background(color = Color(0xFF282727))) {
            Text(text = tag, color = Color.White, modifier = Modifier.align(Alignment.Center))
        }
    }
}





















