package com.clarkelamothe.notemark.feature_auth.presentation.login

sealed interface LoginAction {
    data object OnLoginClick : LoginAction
    data object OnRegisterClick : LoginAction
    data object OnTogglePasswordVisibility : LoginAction
    data class OnInputEmail(val email: String) : LoginAction
    data class OnInputPassword(val password: String) : LoginAction
}
