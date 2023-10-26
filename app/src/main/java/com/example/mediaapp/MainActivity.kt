package com.example.mediaapp

import android.content.ClipData.Item
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.mediaapp.ui.theme.MediaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaAppTheme {
                // A surface container using the 'background' color from the theme
                YoufollowPageLayout()
                }
            }
        }
    }

@Preview
@Composable
fun YoufollowPageLayout(){
    Box(modifier = Modifier.background(color = colorResource(id = R.color.background))) {
        Column {
            Text(
                text = stringResource(id = R.string.youfollow),
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 18.dp, bottom = 10.dp, top = 10.dp)
                    .height(28.dp)
                    .width(280.dp)
            )
            SearchBar()
            LazyColumn() {
                item {
                    ProfileListItem(name = R.string.benjamin, iconColor = R.color.purple, true)
                }
                item {
                    ProfileListItem(name = R.string.kevin, iconColor = R.color.purple, true)
                }
                item {
                    ProfileListItem(name = R.string.valdemar, iconColor = R.color.lightgreen, true)
                }
                item {
                    ProfileListItem(name = R.string.simon, iconColor = R.color.red, true)
                }
                item {
                    ProfileListItem(name = R.string.david, iconColor = R.color.blue, true)
                }
                item {
                    ProfileListItem(name = R.string.jonathan, iconColor = R.color.teal, true)
                }
                item {
                    ProfileListItem(name = R.string.patrick, iconColor = R.color.lightpurple, true)
                }
                item {
                    ProfileListItem(name = R.string.charlie, iconColor = R.color.blue, true)
                }
                item {
                    ProfileListItem(name = R.string.mikkel, iconColor = R.color.lightpurple, true)
                }
                item {
                    ProfileListItem(name = R.string.tanja, iconColor = R.color.purple, true)
                }
                item {
                    ProfileListItem(name = R.string.valde, iconColor = R.color.purple, true)
                }
                item {
                    ProfileListItem(name = R.string.mads, iconColor = R.color.purple, true)
                }
            }
        }
    }
}

@Preview
@Composable
fun YourfollowersPageLayout(){
    Box(modifier = Modifier.background(color = colorResource(id = R.color.background))) {
        Column {
            Text(
                text = stringResource(id = R.string.yourfollowers),
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 18.dp, bottom = 10.dp, top = 10.dp)
                    .height(28.dp)
                    .width(280.dp)
            )
            SearchBar()
            LazyColumn() {
                item {
                    ProfileListItem(name = R.string.kevin, iconColor = R.color.purple, true)
                }
                item {
                    ProfileListItem(name = R.string.sara, iconColor = R.color.red, false)
                }
                item {
                    ProfileListItem(name = R.string.rasmus, iconColor = R.color.lightgreen, false)
                }
                item {
                    ProfileListItem(name = R.string.valde2, iconColor = R.color.lightgreen, true)
                }
                item {
                    ProfileListItem(name = R.string.mia, iconColor = R.color.blue, false)
                }
                item {
                    ProfileListItem(name = R.string.david, iconColor = R.color.blue, true)
                }
                item {
                    ProfileListItem(name = R.string.lars, iconColor = R.color.lightpurple, false)
                }
                item {
                    ProfileListItem(name = R.string.anna, iconColor = R.color.blue, false)
                }
                item {
                    ProfileListItem(name = R.string.jonathan, iconColor = R.color.teal, true)
                }
                item {
                    ProfileListItem(name = R.string.john, iconColor = R.color.red, true)
                }
                item {
                    ProfileListItem(name = R.string.ida, iconColor = R.color.red, false)
                }
                item {
                    ProfileListItem(name = R.string.gustav, iconColor = R.color.red, false)
                }
                item {
                    ProfileListItem(name = R.string.ph4ntom, iconColor = R.color.red, false)
                }
            }
        }
    }
}


@Composable
fun ProfileListItem(name: Int, iconColor: Int, followstatus: Boolean) {
    var isFollowing by remember { mutableStateOf(followstatus) }
    var tempString = stringResource(id = name)
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .background(colorResource(id = R.color.background)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start ){
        Box(modifier = Modifier
            .padding(start = 16.dp)
            .width(40.dp)
            .height(40.dp)
            .clip(CircleShape)
            .background(colorResource(id = iconColor)),
            contentAlignment = Alignment.Center
            ) {
            Text (
                text = tempString[0].toString(),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 3.dp)
                    .height(28.dp)
            )
        }
        Text (
            text = stringResource(id = name),
            fontSize = 16.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 16.dp, top = 3.dp)
                .height(28.dp)
                .width(280.dp)
        )
        Icon(
            imageVector = if (isFollowing) Icons.Filled.PersonRemove else Icons.Filled.PersonAddAlt1,
            contentDescription = if (isFollowing) "Person_Remove" else "Person_Add",
            tint = colorResource(id = R.color.white),
            modifier = Modifier
                .clickable {
                    // Toggle the follow status when clicked
                    isFollowing = !isFollowing
                }
        )

    }
}

@Composable
fun SearchBar() {
    Box(modifier = Modifier
        .padding(0.dp)
        .fillMaxWidth()
        .height(56.dp)
        .background(color = Color(0xFF2B2930), shape = RoundedCornerShape(size = 28.dp)),
        contentAlignment = Alignment.CenterStart) {
        Row {
            Text (
                text = stringResource(id = R.string.searchtext),
                fontSize = 16.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFCAC4D0),
                textAlign = TextAlign.Start,
                letterSpacing = 0.5.sp,
                modifier = Modifier
                    .padding(start = 20.dp, top = 3.dp)
                    .height(28.dp)
                    .width(328.dp)
            )
            Icon(modifier = Modifier
                .padding(top = 2.dp)
                .size(24.dp),
                imageVector = Icons.Filled.Search,
                contentDescription = "search",
                tint = Color(0xFFCAC4D0))
        }
    }
}