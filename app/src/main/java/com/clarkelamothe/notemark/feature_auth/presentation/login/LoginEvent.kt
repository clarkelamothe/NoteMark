package com.clarkelamothe.notemark.feature_auth.presentation.login

sealed interface LoginEvent {
    data object OnLoginSuccess: LoginEvent
    data object OnLoginError: LoginEvent
}
