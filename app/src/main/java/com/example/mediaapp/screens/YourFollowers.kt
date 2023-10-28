package com.example.mediaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediaapp.R
import com.example.mediaapp.ui.nav.TopNavBarB

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YourfollowersPageLayout(navController: NavController, drawerState: DrawerState){
    Box(
        modifier = Modifier
            .padding(bottom = 75.dp)
            .background(color = colorResource(id = R.color.background_settings)
        )) {
        Column {
            TopNavBarB(navController = navController, drawerState = drawerState)
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
            searchBar(R.string.searchtext)
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