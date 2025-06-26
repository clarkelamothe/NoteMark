package com.clarkelamothe.notemark.feature_auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clarkelamothe.notemark.core.domain.util.DataError
import com.clarkelamothe.notemark.core.domain.util.Result
import com.clarkelamothe.notemark.feature_auth.domain.AuthRepository
import com.clarkelamothe.notemark.feature_auth.domain.UserDataValidator
import com.clarkelamothe.notemark.feature_auth.presentation.login.LoginAction
import com.clarkelamothe.notemark.feature_auth.presentation.login.LoginEvent
import com.clarkelamothe.notemark.feature_auth.presentation.login.LoginState
import com.clarkelamothe.notemark.feature_auth.presentation.register.RegisterAction
import com.clarkelamothe.notemark.feature_auth.presentation.register.RegisterEvent
import com.clarkelamothe.notemark.feature_auth.presentation.register.RegisterState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState = _registerState.asStateFlow()

    private val loginEventChannel = Channel<LoginEvent>()
    val loginEvents = loginEventChannel.receiveAsFlow()

    private val registerEventChannel = Channel<RegisterEvent>()
    val registerEvents = registerEventChannel.receiveAsFlow()

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
                val emailValid = userDataValidator.isValidEmail(email)
                val passwordValid =
                    userDataValidator.validatePassword(password).isValidPassword

                val canLogin = emailValid && passwordValid

                loginState.copy(
                    email = email,
                    password = password,
                    emailError = email.isNotEmpty() && !emailValid,
                    passwordError = password.isNotEmpty() && !passwordValid,
                    canLogin = canLogin
                )
            }
        }.launchIn(viewModelScope)

        combine(
            username,
            email,
            password,
            repeatPassword
        ) { username, email, password, repeatPassword ->
            val usernameValid = userDataValidator.isValidUsername(username)
            val emailValid = userDataValidator.isValidEmail(email)
            val passwordValid = userDataValidator.validatePassword(password).isValidPassword

            val passwordsMatched = password == repeatPassword
            val canRegister = usernameValid && emailValid && passwordValid && passwordsMatched

            _registerState.update { registerState ->
                registerState.copy(
                    email = email,
                    username = username,
                    password = password,
                    repeatPassword = repeatPassword,
                    emailError = email.isNotEmpty() && !emailValid,
                    usernameError = username.isNotEmpty() && !usernameValid,
                    passwordError = password.isNotEmpty() && !passwordValid,
                    repeatPasswordError = repeatPassword.isNotEmpty() && !passwordsMatched,
                    canRegister = canRegister
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnInputEmail -> email.update { action.email }
            is LoginAction.OnInputPassword -> password.update { action.password }
            LoginAction.OnLoginClick -> {
                viewModelScope.launch {
                    _loginState.update { it.copy(isLoggingIn = true) }
                    delay(2000)
                    loginEventChannel.send(LoginEvent.OnLoginError)
                    _loginState.update { it.copy(isLoggingIn = false) }
                }
            }

            else -> {} /* No-op */
        }
    }

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnInputUsername -> username.update { action.username }
            is RegisterAction.OnInputEmail -> email.update { action.email }
            is RegisterAction.OnInputPassword -> password.update { action.password }
            is RegisterAction.OnRepeatPassword -> repeatPassword.update { action.password }
            RegisterAction.OnRegisterClick -> {
                viewModelScope.launch {
                    _registerState.update { it.copy(isRegistering = true) }
                    val result = repository.register(
                        username.value, email.value, password.value
                    )
                    _registerState.update { it.copy(isRegistering = false) }

                    when (result) {
                        is Result.Success -> registerEventChannel.send(RegisterEvent.OnRegisterSuccess)
                        is Result.Error -> {
//                            if (result.error == DataError.Network.CONFLICT) {}
                            registerEventChannel.send(RegisterEvent.OnRegisterError)
                        }
                    }
                }
            }

            else -> {} /* No-op */
        }
    }
}
