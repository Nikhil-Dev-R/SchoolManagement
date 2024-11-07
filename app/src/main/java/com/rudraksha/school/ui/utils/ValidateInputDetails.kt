package com.rudraksha.school.ui.utils

import com.rudraksha.school.viewmodel.SchoolUiState

fun validateRegistrationInput(
    uiState: SchoolUiState,
    name: String,
    email: String,
    password: String,
    confirmPassword: String
) {
    // Check if any field is empty
    if (name.isBlank()) {
        uiState.errorMessage = "Full name is required"
    }
    if (email.isBlank()) {
        uiState.errorMessage = "Email is required"
    }
    if (password.isBlank()) {
        uiState.errorMessage = "Password is required"
    }
    if (confirmPassword.isBlank()) {
        uiState.errorMessage = "Please confirm your password"
    }

    // Check if email is valid
    if (!isValidEmail(email)) {
        uiState.errorMessage = "Please enter a valid email address"
    }

    // Check password strength (Minimum length of 6 characters, containing numbers and letters)
    if (password.length < 8) {
        uiState.errorMessage = "Password should be at least 8 characters"
    }
    if (!password.any { it.isDigit() }) {
        uiState.errorMessage = "Password should contain at least one number"
    }
    if (!password.any { it.isLetter() }) {
        uiState.errorMessage = "Password should contain at least one letter"
    }
    if (!password.any { it.isUpperCase() }) {
        uiState.errorMessage = "Password should contain at least one uppercase letter"
    }
    if (!password.any { it.isLowerCase() }) {
        uiState.errorMessage = "Password should contain at least one lowercase letter"
    }
    if (!password.any { it in "!@#$%^&*()_+-=[]{}|;:'\",.<>?/`~" }) {
        uiState.errorMessage = "Password should contain at least one special character"
    }

    // Check if passwords match
    if (password != confirmPassword) {
        uiState.errorMessage = "Passwords do not match"
    }
    uiState.errorMessage = ""
}

fun isValidEmail(email: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}"
    return email.matches(Regex(emailPattern))
}

fun validateLoginInput(
    uiState: SchoolUiState,
    email: String,
    password: String
) {
    // Check if any field is empty
    if (email.isBlank()) {
        uiState.errorMessage = "Email is required"
    }
    if (password.isBlank()) {
        uiState.errorMessage = "Password is required"
    }
    uiState.errorMessage = ""
}