package com.example.mediaapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mediaapp.viewModels.ProfileCustomizationViewModel

@Composable
fun ProfileCustomizationPopup(viewModel: ProfileCustomizationViewModel = viewModel(), openDialog: Boolean, closeDialog: () -> Unit) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = { closeDialog() },
            title = { Text("Customize Account") },
            text = {
                Column {
                    /*TextfieldForUsername(viewModel)
                    TextfieldForName(viewModel)
                    TextfieldForLocation(viewModel)
                    TextfieldForDescription(viewModel)*/
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.updateAccountDetails()
                    closeDialog()
                }) {
                    Text("Save Changes")
                }
            },
            dismissButton = {
                TextButton(onClick = { closeDialog() }) {
                    Text("Cancel")
                }
            }
        )
    }
}