package com.clarkelamothe.notemark.feature_auth.presentation.register

data class RegisterState(
    val username: String = "",
    val usernameError: Boolean = false,
    val email: String = "",
    val emailError: Boolean = false,
    val password: String = "",
    val passwordError: Boolean = false,
    val repeatPassword: String = "",
    val repeatPasswordError: Boolean = false,
    val canRegister: Boolean = false,
    val isRegistering: Boolean = false
)
