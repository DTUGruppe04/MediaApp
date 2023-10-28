package com.example.mediaapp.screens

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
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.navigation.NavController
import com.example.mediaapp.ui.theme.MediaAppTheme
import com.example.mediaapp.R
import com.example.mediaapp.ui.nav.TopNavBarB

/*
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
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YoufollowPageLayout(navController: NavController, drawerState: DrawerState){
    Box(
        modifier = Modifier
            .padding(bottom = 75.dp)
            .background(color = colorResource(id = R.color.background_settings)
        )) {
        Column {
            TopNavBarB(navController = navController, drawerState = drawerState)
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
            searchBar(R.string.searchtext)
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
@Composable
fun ProfileListItem(name: Int, iconColor: Int, followstatus: Boolean) {
    var isFollowing by remember { mutableStateOf(followstatus) }
    var tempString = stringResource(id = name)

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .background(colorResource(id = R.color.background_settings)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start ){
        Box(modifier = Modifier
            .padding(start = 16.dp)
            .size(40.dp)
            .clip(CircleShape)
            .background(colorResource(id = iconColor)),
            contentAlignment = Alignment.Center
        ) {
            Text (
                text = tempString[0].toString(),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                color = colorResource(id = R.color.white),
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
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 16.dp, top = 3.dp)
                .size(width = 280.dp, height = 28.dp)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchBar(searchText: Int) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(text = stringResource(id = searchText), color = Color(0xFFCAC4D0)) },
        trailingIcon = { Icon(
            Icons.Filled.Search,
            contentDescription = null,
            tint = Color(0xFFCAC4D0)) },
        modifier = Modifier.fillMaxWidth().height(56.dp),
        shape = RoundedCornerShape(size = 28.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = colorResource(R.color.lightbackground),
            textColor = Color(0xFFCAC4D0),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent)
    )
}