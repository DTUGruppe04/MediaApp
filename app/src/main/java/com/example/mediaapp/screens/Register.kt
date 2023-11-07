package com.example.mediaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediaapp.R
import com.example.mediaapp.Screen

@Composable
fun CreateAccountPageLayout(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(R.color.login_background_color)
            )
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
            contentAlignment = Alignment.Center) {
            Text(
                stringResource(R.string.login_top_name),
                fontSize = 22.sp,
                lineHeight = 28.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        MainTitleText(R.string.login_create_account)
        SubTitleText(R.string.login_create_account_please)
        TextfieldForUsername()
        TextfieldForEmail()
        TextfieldForPassword()
        TextfieldForConfirmPassword()
        Button(
            onClick = {
            navController.navigate(Screen.Login.route)
        },
            modifier = Modifier
                .width(152.dp)
                .height(76.dp)
                .padding(top = 36.dp, end = 29.dp)
                .align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.login_button)
            )
        ) {
            Text(
                stringResource(R.string.login_create_account_sign_up),
                color = colorResource(R.color.login_button_text)
            )
        }
        BottomSignText(R.string.login_already_account, R.string.login_already_account_sign, navController)
    }
}