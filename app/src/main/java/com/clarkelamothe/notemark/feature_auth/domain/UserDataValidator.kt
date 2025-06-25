package com.clarkelamothe.notemark.feature_auth.domain

class UserDataValidator(
    private val patternValidator: PatternValidator
) {
    fun isValidUsername(username: String) = username.trim().length >= MIN_USERNAME_LENGTH

    fun isValidEmail(email: String) = patternValidator.matches(email.trim())

    fun validatePassword(password: String): PasswordValidationState {
        val hasMinLength = password.length >= MIN_PASSWORD_LENGTH
        val hasDigit = password.any { it.isDigit() }
        val hasLowerCaseCharacter = password.any { it.isLowerCase() }
        val hasUpperCaseCharacter = password.any { it.isUpperCase() }

        return PasswordValidationState(
            hasMinLength = hasMinLength,
            hasNumber = hasDigit,
            hasLowerCaseCharacter = hasLowerCaseCharacter,
            hasUpperCaseCharacter = hasUpperCaseCharacter
        )
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 5
        const val MIN_USERNAME_LENGTH = 4
    }
}
