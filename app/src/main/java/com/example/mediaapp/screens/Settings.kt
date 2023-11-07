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
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediaapp.R
import com.example.mediaapp.ui.nav.TopNavBarB
import com.example.mediaapp.ui.theme.MediaAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingpageLayout(navController: NavController, drawerState: DrawerState) {
    MediaAppTheme {
        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxHeight()) {
            Column {
                TopNavBarB(navController = navController, drawerState = drawerState)
                Text (
                    text = stringResource(id = R.string.settings),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Start,
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

}

@Composable
fun SettingsItem(settingName: Int, icon: ImageVector, settingDesc: Int = R.string.settingnodesc) {
    Box(modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .defaultMinSize(minHeight = 76.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon( modifier = Modifier
                .padding(start = 12.dp)
                .size(24.dp), imageVector = icon, contentDescription = icon.name, tint = MaterialTheme.colorScheme.primary)
            Column() {
                Text (
                    text = stringResource(id = settingName),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 12.dp)
                )
                if (settingDesc.equals(R.string.settingnodesc)) {
                    Text(
                        text = stringResource(id = settingDesc),
                        fontSize = 0.sp,
                        lineHeight = 0.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(start = 20.dp, top = 12.dp)
                    )
                } else {
                    Text (
                        text = stringResource(id = settingDesc),
                        style = MaterialTheme.typography.labelMedium,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(start = 20.dp, bottom = 12.dp)
                            .width(269.dp)
                    )
                }

            }
        }
    }
}