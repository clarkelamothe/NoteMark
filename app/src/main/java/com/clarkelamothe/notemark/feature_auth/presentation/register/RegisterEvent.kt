package com.clarkelamothe.notemark.feature_auth.presentation.register

sealed interface RegisterEvent {
    data object OnRegisterSuccess : RegisterEvent
    data object OnRegisterError : RegisterEvent
}
