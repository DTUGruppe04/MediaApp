package com.example.mediaapp.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.mediaapp.Screen
import com.example.mediaapp.backend.database.DatabaseHandler

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

/**
 * ViewModel for the login page.
 * Handles user registration and input validation.
 */
class LoginPageViewModel : ViewModel() {

    private val databaseHandler = DatabaseHandler.getInstance()

    // Variables to hold user input
    var errorText = mutableStateOf("")
    var email = ""
    var password = ""
    var confirmPassword = ""
    var username = ""

    /**
     * Handles the registration flow.
     * Validates user input and registers the user if the input is valid.
     */
    fun registerFlow(navController: NavController) {
        try {
            validateInput(true)
        } catch (e: IllegalArgumentException) {
            errorText.value = e.message ?: ERROR
        }
        if (errorText.value.isEmpty()) {
            registerUser(navController)
        }
    }

    /**
     * Registers the user with Firebase Authentication.
     * If registration is successful, updates the user's profile and adds the user to the database.
     */
    private fun registerUser(navController: NavController) {
        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Firebase.auth.currentUser?.let { user ->
                    // Update the user's display name
                    user.updateProfile(com.google.firebase.auth.UserProfileChangeRequest.Builder().setDisplayName(username).build())
                    // Add the user to the database
                    databaseHandler.updateUserInDatabase(user.uid, createUserMap())
                }
                errorText.value = "User created successfully"
                navController.navigate(Screen.Login.route)
            } else {
                errorText.value = when {
                    task.exception.toString().contains("email address is badly formatted") -> "Please enter a valid email address"
                    task.exception.toString().contains("The email address is already in use by another account") -> "This email address is already in use by another account"
                    else -> "Failed, try again"
                }
            }
        }
    }

    /**
     * Handles the login flow.
     * Validates user input and logs in the user if the input is valid.
     */
    fun loginFlow(navController: NavController) {
        try {
            validateInput(false)
        } catch (e: IllegalArgumentException) {
            errorText.value = e.message ?: ERROR
        }
        if (errorText.value.isEmpty()) {
            loginUser(navController)
        }
    }

    /**
     * Logs in the user with Firebase Authentication.
     * If login is successful, clears the error text. Otherwise, sets the error text to an appropriate error message.
     */
    private fun loginUser(navController: NavController) {
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                errorText.value = ""
                navController.navigate(Screen.MainScreen.route)
            } else {
                errorText.value = when {
                    task.exception.toString().contains("email address is badly formatted") -> "Please enter a valid email address"
                    task.exception.toString().contains("supplied auth credential is incorrect") -> "The email or password is incorrect"
                    task.exception.toString().contains("Access to this account has been temporarily disabled due to many failed login attempts") -> "Too many failed login attempts for this account. Please try again later"
                    else -> "Failed, try again"
                }
            }
        }
    }

    companion object {
        const val ERROR_EMPTY_FIELDS = "Please fill in all fields"
        const val ERROR_INVALID_EMAIL = "Please enter a valid email address"
        const val ERROR_SHORT_PASSWORD = "Password must be at least 8 characters"
        const val ERROR_PASSWORD_MISMATCH = "Password does not match"
        const val ERROR_SHORT_USERNAME = "Username must be at least 4 characters"
        const val ERROR_LONG_USERNAME = "Username must be less than 20 characters"
        const val ERROR_USERNAME_SPACES = "Username cannot contain spaces"
        const val ERROR_USERNAME_SPECIAL_CHARACTERS = "Username cannot contain special characters"
        const val ERROR = "Failed, try again"
    }

    private fun validateInput(registration: Boolean) {
        when {
            listOf(email, password).any { it.isEmpty() } -> throw IllegalArgumentException(ERROR_EMPTY_FIELDS)
            !email.contains("@") || !email.contains(".") -> throw IllegalArgumentException(ERROR_INVALID_EMAIL)
            password.length < 8 -> throw IllegalArgumentException(ERROR_SHORT_PASSWORD)
            registration && listOf(username, confirmPassword).any { it.isEmpty() } -> throw IllegalArgumentException(ERROR_EMPTY_FIELDS)
            registration && password != confirmPassword -> throw IllegalArgumentException(ERROR_PASSWORD_MISMATCH)
            registration && username.length < 4 -> throw IllegalArgumentException(ERROR_SHORT_USERNAME)
            registration && username.length > 20 -> throw IllegalArgumentException(ERROR_LONG_USERNAME)
            registration && username.contains(" ") -> throw IllegalArgumentException(ERROR_USERNAME_SPACES)
            registration && username.any { it in "@.#\$[]/\\%^&*()+=?!<>,;:\"{}|~`'" } -> throw IllegalArgumentException(ERROR_USERNAME_SPECIAL_CHARACTERS)
            else -> errorText.value = ""
        }
    }

    private fun createUserMap() = hashMapOf(
        "username" to username,
        "name" to "",
        "location" to "",
        "followers" to listOf<String>(),
        "following" to listOf<String>(),
        "description" to "",
        "profilePicture" to "",
        "stats" to hashMapOf(
            "watched" to 0,
            "reviews" to 0,
            "rated" to 0,
            "recommends" to 0,
            "saved" to 0
        ),
        "watchlist" to listOf<String>()
    )
}
