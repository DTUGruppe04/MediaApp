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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mediaapp.R
import com.example.mediaapp.Screen
import com.example.mediaapp.ui.theme.MediaAppTheme
import com.example.mediaapp.viewModels.LoginPageViewModel

@Composable
fun CreateAccountPageLayout(navController: NavController,
                            viewModel: LoginPageViewModel = viewModel()
) {
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
                Text(
                    stringResource(R.string.login_top_name),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }
            MainTitleText(R.string.login_create_account)
            SubTitleText(R.string.login_create_account_please)
            TextfieldForUsername(viewModel)
            TextfieldForEmail(viewModel)
            TextfieldForPassword(viewModel)
            TextfieldForConfirmPassword(viewModel)
            Button(
                onClick = {
                    if (viewModel.email.isEmpty() || viewModel.password.isEmpty() || viewModel.username.isEmpty() || viewModel.confirmPassword.isEmpty()) {
                        viewModel.setErrorMessage("Please fill in all fields")
                    } else if (viewModel.password != viewModel.confirmPassword) {
                        viewModel.setErrorMessage("Password does not match")
                    } else if (viewModel.password.length < 8) {
                        viewModel.setErrorMessage("Password must be at least 8 characters")
                    } else if (viewModel.validateUsername(viewModel.username).isNotEmpty()) {
                        viewModel.setErrorMessage(viewModel.validateUsername(viewModel.username))
                    } else {
                        viewModel.register(
                            viewModel.email,
                            viewModel.password,
                            viewModel.username
                        ) {
                            if (it == null) {
                                viewModel.updateUser(viewModel.username) {
                                    if (it == null) {
                                        navController.navigate(Screen.Login.route)
                                    }
                                }
                            } else if (it.toString().contains("email address is badly formatted")) {
                                viewModel.setErrorMessage("Please enter a valid email address")
                            } else if (it.toString().contains("The email address is already in use by another account")) {
                                viewModel.setErrorMessage("This email address is already in use by another account")
                            }
                        }
                    }
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
                Text(
                    stringResource(R.string.login_create_account_sign_up),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            if (viewModel.errorText.isNotEmpty()) {
                Text(text = viewModel.errorText,
                    modifier = Modifier
                        .padding(top = 11.dp, end = 29.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
            BottomSignText(R.string.login_already_account, R.string.login_already_account_sign, navController)
        }
    }

}