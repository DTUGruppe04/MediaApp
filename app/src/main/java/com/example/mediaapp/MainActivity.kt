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
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PersonAddAlt1
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CircleNotifications
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
                SettingpageLayout()
            }
        }
    }
}

@Preview
@Composable
fun SettingpageLayout() {
    Box(modifier = Modifier.background(colorResource(id = R.color.background)).fillMaxHeight()) {
        Column {
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
        .background(colorResource(id = R.color.background))
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
