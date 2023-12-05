package com.example.mediaapp.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mediaapp.models.DatabaseHandler
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

/**
 * ViewModel for the login page.
 * Handles user registration and input validation.
 */
class LoginPageViewModel : ViewModel() {

    // Instance of DatabaseHandler for database operations
    private val databaseHandler = DatabaseHandler()

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
    fun registerFlow() {
        when {
            // Check if any field is empty
            listOf(email, password, username, confirmPassword).any { it.isEmpty() } -> errorText.value = "Please fill in all fields"
            // Check if password and confirm password match
            password != confirmPassword -> errorText.value = "Password does not match"
            // Check if email is valid
            !email.contains("@") || !email.contains(".") -> errorText.value = "Please enter a valid email address"
            // Check if password is at least 8 characters long
            password.length < 8 -> errorText.value = "Password must be at least 8 characters"
            // Check if username is valid
            validateUsername(username).isNotEmpty() -> errorText.value = validateUsername(username)
            // If all checks pass, register the user
            else -> registerUser()
        }
    }

    /**
     * Registers the user with Firebase Authentication.
     * If registration is successful, updates the user's profile and adds the user to the database.
     */
    private fun registerUser() {
        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Firebase.auth.currentUser?.let { user ->
                    // Update the user's display name
                    user.updateProfile(com.google.firebase.auth.UserProfileChangeRequest.Builder().setDisplayName(username).build())
                    // Add the user to the database
                    databaseHandler.updateUserInDatabase(user.uid, createUserMap())
                }
                errorText.value = ""
            } else {
                // Handle registration errors
                errorText.value = when {
                    task.exception.toString().contains("email address is badly formatted") -> "Please enter a valid email address"
                    task.exception.toString().contains("The email address is already in use by another account") -> "This email address is already in use by another account"
                    else -> "Registration failed"
                }
            }
        }
    }

    /**
     * Handles the login flow.
     * Validates user input and logs in the user if the input is valid.
     */
    fun loginFlow() {
        when {
            // Check if any field is empty
            listOf(email, password).any { it.isEmpty() } -> errorText.value = "Please fill in all fields"
            // Check if email is valid
            !email.contains("@") || !email.contains(".") -> errorText.value = "Please enter a valid email address"
            // If all checks pass, log in the user
            else -> loginUser()
        }
    }

    /**
     * Logs in the user with Firebase Authentication.
     * If login is successful, clears the error text. Otherwise, sets the error text to an appropriate error message.
     */
    private fun loginUser() {
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                errorText.value = ""
            } else {
                // Handle login errors
                errorText.value = when {
                    task.exception.toString().contains("email address is badly formatted") -> "Please enter a valid email address"
                    task.exception.toString().contains("supplied auth credential is incorrect") -> "The email ore password is incorrect"
                    task.exception.toString().contains("Access to this account has been temporarily disabled due to many failed login attempts") -> "Too many failed login attempts. Please try again later"
                    else -> "Login failed"
                }
            }
        }
    }

    /**
     * Creates a map of user data to be stored in the database.
     * @return A map of user data.
     */
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

    /**
     * Validates the username.
     * Checks if the username is at least 4 characters long, less than 20 characters long, does not contain spaces, and does not contain special characters.
     * @param username The username to validate.
     * @return An error message if the username is invalid, or an empty string if the username is valid.
     */
    private fun validateUsername(username: String) = when {
        username.length < 4 -> "Username must be at least 4 characters"
        username.length > 20 -> "Username must be less than 20 characters"
        username.contains(" ") -> "Username cannot contain spaces"
        username.any { it in "@.#\$[]/\\%^&*()+=?!<>,;:\"{}|~`'" } -> "Username cannot contain special characters"
        else -> ""
    }
}
