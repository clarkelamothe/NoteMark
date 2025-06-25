package com.clarkelamothe.notemark.feature_auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clarkelamothe.notemark.feature_auth.presentation.login.LoginAction
import com.clarkelamothe.notemark.feature_auth.presentation.login.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update

class AuthViewModel : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    private val email = MutableStateFlow("")
    private val password = MutableStateFlow("")

    init {
        combine(
            email,
            password
        ) { email, password ->
            _loginState.update { loginState ->
                loginState.copy(
                    email = email,
                    password = password,
                    canLogin = email.isNotBlank() && password.isNotBlank()
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnInputEmail -> email.update { action.email }
            is LoginAction.OnInputPassword -> password.update { action.password }
            LoginAction.OnLoginClick -> {}
            LoginAction.OnTogglePasswordVisibility -> {}
            else -> {} /* No-op */
        }
    }
}
