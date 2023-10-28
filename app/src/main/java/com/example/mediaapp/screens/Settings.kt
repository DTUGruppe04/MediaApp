package com.example.mediaapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CircleNotifications
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
fun SettingpageLayout(navController: NavController, drawerState: DrawerState) {
    Box(modifier = Modifier.background(colorResource(id = R.color.background_settings)).fillMaxHeight()) {
        Column {
            TopNavBarB(navController = navController, drawerState = drawerState)
            Text (
                text = stringResource(id = R.string.settings),
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFE6E0E9),
                textAlign = TextAlign.Start,
                letterSpacing = 0.5.sp,
                modifier = Modifier
                    .padding(start = 28.dp, top = 10.dp, bottom = 10.dp)
            )
            SettingsItem(settingName = R.string.youraccount, icon = Icons.Outlined.AccountCircle, settingDesc = R.string.youraccountdesc)
            SettingsItem(settingName = R.string.accountsecurity, icon = Icons.Outlined.Lock, settingDesc = R.string.accountsecuritydesc)
            SettingsItem(settingName = R.string.notifications, icon = Icons.Outlined.CircleNotifications, settingDesc = R.string.notificationsdesc)
            SettingsItem(settingName = R.string.accessibility, icon = Icons.Outlined.Language, settingDesc = R.string.accessibilitydesc)
            SettingsItem(settingName = R.string.proxy, icon = Icons.Outlined.RemoveRedEye)
        }
    }
}

@Composable
fun SettingsItem(settingName: Int, icon: ImageVector, settingDesc: Int = R.string.settingnodesc) {
    Box(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .background(colorResource(id = R.color.background_settings))
        .defaultMinSize(minHeight = 76.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Icon( modifier = Modifier
                .padding(start = 12.dp)
                .size(24.dp), imageVector = icon, contentDescription = icon.name, tint = colorResource(id = R.color.white))
            Column() {
                Text (
                    text = stringResource(id = settingName),
                    fontSize = 16.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFE6E0E9),
                    textAlign = TextAlign.Start,
                    letterSpacing = 0.5.sp,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 12.dp)
                )
                if (settingDesc.equals(R.string.settingnodesc)) {
                    Text(
                        text = stringResource(id = settingDesc),
                        fontSize = 0.sp,
                        lineHeight = 0.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFE6E0E9),
                        textAlign = TextAlign.Start,
                        letterSpacing = 0.5.sp,
                        modifier = Modifier
                            .padding(start = 20.dp, top = 12.dp)
                    )
                } else {
                    Text (
                        text = stringResource(id = settingDesc),
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Start,
                        color = Color(0xFFCAC4D0),
                        letterSpacing = 0.25.sp,
                        modifier = Modifier
                            .padding(start = 20.dp, bottom = 12.dp)
                            .width(269.dp)
                    )
                }

            }
        }
    }
}