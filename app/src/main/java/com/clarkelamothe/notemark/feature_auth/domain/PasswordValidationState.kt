package com.clarkelamothe.notemark.feature_auth.domain

data class PasswordValidationState(
    val hasMinLength: Boolean = false,
    val hasNumber: Boolean = false,
    val hasSpecialCharacter: Boolean = false
) {
    val isValidPassword: Boolean
        get() = hasMinLength && (hasNumber || hasSpecialCharacter)
}
