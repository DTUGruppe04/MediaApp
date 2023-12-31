package com.example.mediaapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediaapp.ui.theme.MediaAppTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mediaapp.R
import com.example.mediaapp.Screen


@Composable
fun LoginPageLayout(navController: NavController) {
    MediaAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
                contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.login_top_name),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }
            MainTitleText(R.string.login)
            SubTitleText(R.string.login_please)
            TextfieldForEmail()
            TextfieldForPassword()
            Text(stringResource(R.string.login_forgot_password),
                modifier = Modifier
                    .padding(start = 250.dp, top = 11.dp, end = 29.dp)
                    .clickable {
                        navController.navigate(Screen.ForgotPassword.route)
                    }
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textDecoration = TextDecoration.Underline
            )
            Button(onClick = {
                navController.navigate(Screen.MainScreen.route)
            },
                modifier = Modifier
                    .width(152.dp)
                    .height(76.dp)
                    .padding(top = 36.dp, end = 29.dp)
                    .align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(stringResource(R.string.login_big),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            BottomSignText(R.string.login_missing_account, R.string.login_missing_account_sign, navController)
        }
    }

}

@Composable
fun MainTitleText(string: Int) {
    Text(stringResource(string),
        fontSize = 30.sp,
        lineHeight = 28.sp,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .padding(start = 29.dp, top = 45.dp)
    )
}

@Composable
fun SubTitleText(string: Int) {
    Text(stringResource(string),
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .padding(start = 30.dp)
    )
}

@Composable
fun BottomSignText(string1: Int, string2: Int, navController: NavController) {
    Column(modifier = Modifier
        .padding(bottom = 27.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier
                .clickable {
                    if(string1 == R.string.login_missing_account) {
                        navController.navigate(Screen.Register.route)
                    } else {
                        navController.navigate(Screen.Login.route)
                    }
                },
            text = buildAnnotatedString (
            )
            {
                withStyle(style = SpanStyle(
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                ) {
                    append(stringResource(string1))
                }
                withStyle(
                    style = SpanStyle(
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        textDecoration = TextDecoration.Underline,
                    )
                ) {
                    append(stringResource(string2))
                }
            }
        )
    }
}

@Composable
private fun textFieldModifier() = Modifier
    .padding(start = 29.dp, top = 16.dp, end = 29.dp)
    .fillMaxWidth()
    .height(56.dp)
    .clip(RoundedCornerShape(10.dp))

@Composable
private fun labelStyle(text: String) = Text(
    text = text,
    color = MaterialTheme.colorScheme.primary,
    style = MaterialTheme.typography.labelMedium
)

@Composable
private fun placeholderStyle(text: String) = Text(
    text = text,
    color = MaterialTheme.colorScheme.onSurfaceVariant,
    fontStyle = FontStyle.Italic,
    style = MaterialTheme.typography.titleMedium,
    letterSpacing = 0.5.sp
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun textFieldColors() = TextFieldDefaults.textFieldColors(
    containerColor = MaterialTheme.colorScheme.surfaceVariant,
    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
    unfocusedIndicatorColor = Color.Transparent
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextfieldForUsername() {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        modifier = textFieldModifier(),
        value = text,
        onValueChange = { text = it },
        label = {labelStyle("Username")},
        placeholder = {placeholderStyle("Enter your username")},
        colors = textFieldColors(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextfieldForEmail() {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        modifier = textFieldModifier(),
        value = text,
        onValueChange = { text = it },
        label = {labelStyle("Email")},
        placeholder = {placeholderStyle("Enter your email")},
        colors = textFieldColors(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun PasswordVisibilityToggle(passwordVisible: Boolean, onToggle: () -> Unit) {
    val image = if (passwordVisible)
        Icons.Filled.Visibility
    else Icons.Filled.VisibilityOff

    val description = if (passwordVisible) "Hide password" else "Show password"

    IconButton(onClick = onToggle) {
        Icon(imageVector = image, description)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextfieldForPassword() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        modifier = textFieldModifier(),
        value = text,
        onValueChange = { text = it },
        label = { labelStyle("Password") },
        placeholder = { placeholderStyle("Enter your password") },
        colors = textFieldColors(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            PasswordVisibilityToggle(passwordVisible) {
                passwordVisible = !passwordVisible
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextfieldForConfirmPassword() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        modifier = textFieldModifier(),
        value = text,
        onValueChange = { text = it },
        label = { labelStyle("Confirm Password") },
        placeholder = { placeholderStyle("Confirm your password") },
        colors = textFieldColors(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            PasswordVisibilityToggle(passwordVisible) {
                passwordVisible = !passwordVisible
            }
        }
    )
}

/*
@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    MediaAppTheme {
        //LoginPageLayout()
        //CreateAccountPageLayout()
        ForgotPasswordPageLayout()
    }
}

 */