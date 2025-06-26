package com.clarkelamothe.notemark.feature_auth.presentation.login

data class LoginState(
    val email: String = "",
    val emailError: Boolean = false,
    val password: String = "",
    val passwordError: Boolean = false,
    val canLogin: Boolean = false,
    val isLoggingIn: Boolean = false
)