package com.example.mediaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediaapp.ui.theme.MediaAppTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MediaAppTheme {
                LoginPageLayout()
            }
        }
    }
}
//0xFF2E2E2E

@Composable
fun LoginPageLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.login_background_color))
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
            contentAlignment = Alignment.Center) {
            Text(stringResource(R.string.login_top_name),
                fontSize = 22.sp,
                lineHeight = 28.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        Text(stringResource(R.string.login),
            fontSize = 30.sp,
            lineHeight = 28.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 29.dp, top = 45.dp))
        Text(stringResource(R.string.please),
            fontSize = 12.sp,
            lineHeight = 28.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 30.dp))
        TextfieldForEmail()
        TextfieldForPassword()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextfieldForEmail() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        modifier = Modifier
            .padding(start = 29.dp, top = 16.dp, end = 29.dp)
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(10.dp)),
        value = text,
        onValueChange = {
            text = it
        },
        label = { Text(text = "Email",
            color = colorResource(R.color.login_textfield_label),
            fontSize = 12.sp,
            lineHeight = 16.sp) },
        placeholder = { Text(text = "Enter your email",
            color = colorResource(R.color.login_textfield_text),
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = colorResource(R.color.login_textfield_background),
            textColor = colorResource(R.color.login_textfield_text),
            focusedIndicatorColor = colorResource(R.color.login_textfield_label),
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextfieldForPassword() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    TextField(
        modifier = Modifier
            .padding(start = 29.dp, top = 16.dp, end = 29.dp)
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(10.dp)),
        value = text,
        onValueChange = {
            text = it
        },
        label = { Text(text = "Password",
            color = colorResource(R.color.login_textfield_label),
            fontSize = 12.sp,
            lineHeight = 16.sp) },
        placeholder = { Text(text = "Enter your password",
            color = colorResource(R.color.login_textfield_text),
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = colorResource(R.color.login_textfield_background),
            textColor = colorResource(R.color.login_textfield_text),
            focusedIndicatorColor = colorResource(R.color.login_textfield_label),
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            //Description for the visibility icon
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(imageVector  = image, description)
            }
        }

    )
}

@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    MediaAppTheme {
        LoginPageLayout()
    }
}