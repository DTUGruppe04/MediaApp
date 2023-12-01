package com.example.mediaapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mediaapp.Screen
import com.google.firebase.Firebase
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await
import java.io.FileInputStream

class LoginPageViewModel : ViewModel(){

    var errorText by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var confirmPassword by mutableStateOf("")
        private set
    var username by mutableStateOf("")
        private set
    fun updateEmail(input: String) {
        email = input
    }
    fun updatePassword(input: String) {
        password = input
    }
    fun updateUsername(input: String) {
        username = input
    }
    fun updateConfirmPassword(input: String) {
        confirmPassword = input
    }

    fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { onResult(it.exception) }
    }

    fun register(email: String, password: String, username: String, onResult: (Throwable?) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { onResult(it.exception) }
    }

    fun updateUsersUsername(username: String, onResult: (Throwable?) -> Unit) {
        val user = Firebase.auth.currentUser
        val profileUpdates = com.google.firebase.auth.UserProfileChangeRequest.Builder()
            .setDisplayName(username)
            .build()
        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { onResult(it.exception) }
    }

    fun setErrorMessage(error: String) {
        errorText = error
    }

    fun validateUsername(username: String): String {
        if (username.length < 4) {
            return "Username must be at least 4 characters"
        } else if (username.length > 20) {
            return "Username must be less than 20 characters"
        } else if (username.contains(" ")) {
            return "Username cannot contain spaces"
        } else if (username.contains("@") || username.contains(".") || username.contains("#") || username.contains("$") || username.contains("[") || username.contains("]") || username.contains("/") || username.contains("\\") || username.contains("%") || username.contains("^") || username.contains("&") || username.contains("*") || username.contains("(") || username.contains(")") || username.contains("+") || username.contains("=") || username.contains("?") || username.contains("!") || username.contains("<") || username.contains(">") || username.contains(",") || username.contains(";") || username.contains(":") || username.contains("\"") || username.contains("{") || username.contains("}") || username.contains("|") || username.contains("~") || username.contains("`") || username.contains("'")) {
            return "Username cannot contain special characters"
        } else {
            return ""
        }
    }

    fun registerFlow() {
        if (email.isEmpty() || password.isEmpty() || username.isEmpty() || confirmPassword.isEmpty()) {
            setErrorMessage("Please fill in all fields")
        } else if (password != confirmPassword) {
            setErrorMessage("Password does not match")
        } else if (password.length < 8) {
            setErrorMessage("Password must be at least 8 characters")
        } else if (validateUsername(username).isNotEmpty()) {
            setErrorMessage(validateUsername(username))
        } else {
            register(
                email,
                password,
                username
            ) { createAccountThrow ->
                if (createAccountThrow == null) {
                    updateUsersUsername(username) {
                        if (it == null) {
                            setErrorMessage("")

                        }
                    }
                } else if (createAccountThrow.toString().contains("email address is badly formatted")) {
                    setErrorMessage("Please enter a valid email address")
                } else if (createAccountThrow.toString().contains("The email address is already in use by another account")) {
                    setErrorMessage("This email address is already in use by another account")
                }
            }
        }
    }

    fun loginFlow() {
        if(email.isNotEmpty() && password.isNotEmpty() ) {
            authenticate(email, password) {
                if (it == null) {
                    setErrorMessage("")
                } else if (it.toString().contains("email address is badly formatted")) {
                    setErrorMessage("Please enter a valid email address")
                } else if (it.toString().contains("supplied auth credential is incorrect")) {
                    setErrorMessage("Incorrect email or password")
                } else if (it.toString().contains("Access to this account has been temporarily disabled due to many failed login attempts")) {
                    setErrorMessage("Too many failed login attempts. Please try again later")
                }
            }
        } else if (email.isEmpty() && password.isEmpty()) {
            setErrorMessage("Please enter your email and password")
        } else if (email.isEmpty()) {
            setErrorMessage("Please enter your email")
        } else if (password.isEmpty()) {
            setErrorMessage("Please enter your password")
        }
    }

}