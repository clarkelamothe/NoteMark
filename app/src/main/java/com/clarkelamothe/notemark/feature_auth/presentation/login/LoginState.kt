package com.clarkelamothe.notemark.feature_auth.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val canLogin: Boolean = false
)