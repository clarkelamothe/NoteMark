package com.clarkelamothe.notemark.feature_auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clarkelamothe.notemark.feature_auth.domain.UserDataValidator
import com.clarkelamothe.notemark.feature_auth.presentation.login.LoginAction
import com.clarkelamothe.notemark.feature_auth.presentation.login.LoginState
import com.clarkelamothe.notemark.feature_auth.presentation.register.RegisterAction
import com.clarkelamothe.notemark.feature_auth.presentation.register.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update

class AuthViewModel(
    private val userDataValidator: UserDataValidator
) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState = _registerState.asStateFlow()

    private val username = MutableStateFlow("")
    private val email = MutableStateFlow("")
    private val password = MutableStateFlow("")
    private val repeatPassword = MutableStateFlow("")

    init {
        combine(
            email,
            password
        ) { email, password ->
            _loginState.update { loginState ->
                loginState.copy(
                    email = email,
                    password = password,
                    canLogin = userDataValidator.isValidEmail(email) && userDataValidator.validatePassword(
                        password
                    ).isValidPassword
                )
            }
        }.launchIn(viewModelScope)

        combine(
            username,
            email,
            password,
            repeatPassword
        ) { username, email, password, repeatPassword ->
            val canRegister = with(userDataValidator) {
                isValidUsername(username) && isValidEmail(email) && password == repeatPassword &&
                        validatePassword(
                            password
                        ).isValidPassword
            }

            _registerState.update { registerState ->
                registerState.copy(
                    username = username,
                    email = email,
                    password = password,
                    repeatPassword = repeatPassword,
                    canRegister = canRegister
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnInputEmail -> email.update { action.email }
            is LoginAction.OnInputPassword -> password.update { action.password }
            LoginAction.OnLoginClick -> println("Logging In")
            else -> {} /* No-op */
        }
    }

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnInputUsername -> username.update { action.username }
            is RegisterAction.OnInputEmail -> email.update { action.email }
            is RegisterAction.OnInputPassword -> password.update { action.password }
            is RegisterAction.OnRepeatPassword -> repeatPassword.update { action.password }
            RegisterAction.OnRegisterClick -> println("Registering In")
            else -> {} /* No-op */
        }
    }
}
