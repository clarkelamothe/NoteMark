package com.clarkelamothe.notemark.feature_auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.clarkelamothe.notemark.core.presentation.local.LocalOrientation
import com.clarkelamothe.notemark.core.presentation.local.Orientation
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme
import com.clarkelamothe.notemark.feature_auth.presentation.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    viewModel: AuthViewModel = koinViewModel(),
    onGoToRegister: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val orientation = LocalOrientation.current
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    LoginScreen(
        orientation,
        loginState
    ) { action ->
        when (action) {
            LoginAction.OnRegisterClick -> onGoToRegister()
            else -> {} /* No-op */
        }
        viewModel.onAction(action)
    }
}

@Composable
private fun LoginScreen(
    orientation: Orientation?,
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        when (orientation) {
            Orientation.PHONE_PORTRAIT -> LoginScreenPortrait(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize(),
                email = state.email,
                onEmailChange = { email ->
                    onAction(LoginAction.OnInputEmail(email))
                },
                password = state.password,
                onPasswordChange = { password ->
                    onAction(LoginAction.OnInputPassword(password))
                },
                onLogin = {
                    onAction(LoginAction.OnLoginClick)
                },
                canLogin = state.canLogin,
                onRegister = {
                    onAction(LoginAction.OnRegisterClick)
                },
                isLoading = state.isLoading,
                isEmailError = state.emailError,
                isPasswordError = state.passwordError
            )

            Orientation.PHONE_LANDSCAPE -> LoginScreenLandscape(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize(),
                email = state.email,
                password = state.password,
                onEmailChange = { action ->
                    onAction(LoginAction.OnInputEmail(action))
                },
                onPasswordChange = { password ->
                    onAction(LoginAction.OnInputPassword(password))
                },
                onLogin = {
                    onAction(LoginAction.OnLoginClick)
                },
                onRegister = {
                    onAction(LoginAction.OnRegisterClick)
                },
                canLogin = state.canLogin,
                isLoading = state.isLoading,
                isEmailError = state.emailError,
                isPasswordError = state.passwordError
            )

            Orientation.TABLET_PORTRAIT -> LoginScreenPortrait(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainerLowest,
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp
                        )
                    )
                    .padding(
                        vertical = 100.dp,
                        horizontal = 120.dp
                    )
                    .fillMaxSize(),
                headerAlignment = TextAlign.Center,
                email = state.email,
                onEmailChange = { action ->
                    onAction(LoginAction.OnInputEmail(action))
                },
                password = state.password,
                onPasswordChange = { password ->
                    onAction(LoginAction.OnInputPassword(password))
                },
                onLogin = {},
                canLogin = state.canLogin,
                onRegister = {
                    onAction(LoginAction.OnRegisterClick)
                },
                isLoading = state.isLoading,
                isEmailError = state.emailError,
                isPasswordError = state.passwordError
            )

            Orientation.TABLET_LANDSCAPE -> LoginScreenLandscape(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding()),
                onPasswordChange = { password ->
                    onAction(LoginAction.OnInputPassword(password))
                },
                email = state.email,
                password = state.password,
                onEmailChange = { action ->
                    onAction(LoginAction.OnInputEmail(action))
                },
                onLogin = {},
                onRegister = {
                    onAction(LoginAction.OnRegisterClick)
                },
                canLogin = state.canLogin,
                isLoading = state.isLoading,
                isEmailError = state.emailError,
                isPasswordError = state.passwordError
            )

            Orientation.DESKTOP, null -> {}
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    NoteMarkTheme {
        LoginScreenRoot(
            onGoToRegister = {}
        )
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun LoginScreenLandscapePreview() {
    NoteMarkTheme {
        LoginScreenRoot(
            onGoToRegister = {}
        )
    }
}
