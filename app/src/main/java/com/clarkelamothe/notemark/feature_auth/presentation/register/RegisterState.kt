package com.clarkelamothe.notemark.feature_auth.presentation.register

data class RegisterState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val canRegister: Boolean = false
)
