package com.clarkelamothe.notemark.feature_auth.presentation.register

sealed interface RegisterAction {
    data object OnRegisterClick : RegisterAction
    data object OnLoginClick : RegisterAction
    data class OnInputUsername(val username: String) : RegisterAction
    data class OnInputEmail(val email: String) : RegisterAction
    data class OnInputPassword(val password: String) : RegisterAction
    data class OnRepeatPassword(val password: String) : RegisterAction
}
