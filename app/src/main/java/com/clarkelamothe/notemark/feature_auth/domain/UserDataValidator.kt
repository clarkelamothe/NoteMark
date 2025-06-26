package com.clarkelamothe.notemark.feature_auth.domain

class UserDataValidator(
    private val patternValidator: PatternValidator
) {
    fun isValidUsername(username: String) =
        username.trim().length in MIN_USERNAME_LENGTH..MAX_USERNAME_LENGTH

    fun isValidEmail(email: String) = patternValidator.matches(email.trim())

    fun validatePassword(password: String): PasswordValidationState {
        return with(password.trim()) {
            val hasMinLength = length >= MIN_PASSWORD_LENGTH
            val hasDigit = any { it.isDigit() }
            val hasLowerCaseCharacter = any { it.isLowerCase() }
            val hasUpperCaseCharacter = any { it.isUpperCase() }
            val hasSpecialCharacter = any { !it.isLetterOrDigit() }

            PasswordValidationState(
                hasMinLength = hasMinLength,
                hasNumber = hasDigit,
                hasLowerCaseCharacter = hasLowerCaseCharacter,
                hasUpperCaseCharacter = hasUpperCaseCharacter,
                hasSpecialCharacter = hasSpecialCharacter
            )
        }
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 8

        const val MIN_USERNAME_LENGTH = 3
        const val MAX_USERNAME_LENGTH = 20
    }
}
