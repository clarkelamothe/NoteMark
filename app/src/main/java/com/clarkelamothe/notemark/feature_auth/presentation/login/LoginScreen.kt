package com.clarkelamothe.notemark.feature_auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.clarkelamothe.notemark.core.presentation.local.LocalOrientation
import com.clarkelamothe.notemark.core.presentation.local.Orientation
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme
import com.clarkelamothe.notemark.core.presentation.util.ObserveAsEvents
import com.clarkelamothe.notemark.feature_auth.presentation.AuthViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    viewModel: AuthViewModel = koinViewModel(),
    onGoToRegister: () -> Unit,
    onGoToHome: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val orientation = LocalOrientation.current
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.loginEvents) { event ->
        when (event) {
            LoginEvent.OnLoginError -> scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Invalid login credentials"
                )
            }

            LoginEvent.OnLoginSuccess -> onGoToHome()
        }
    }

    LoginScreen(
        orientation = orientation,
        state = loginState,
        snackbarHostState = snackbarHostState
    ) { action ->
        when (action) {
            LoginAction.OnRegisterClick -> onGoToRegister()
            else -> {} /* No-op */
        }
        viewModel.onAction(action)
        snackbarHostState.currentSnackbarData?.dismiss()
    }
}

@Composable
private fun LoginScreen(
    orientation: Orientation?,
    snackbarHostState: SnackbarHostState,
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(WindowInsets.ime.asPaddingValues())
            )
        }
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

            Orientation.DESKTOP, null -> {}
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    NoteMarkTheme {
        LoginScreenRoot(
            onGoToRegister = {},
            onGoToHome = {}
        )
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun LoginScreenLandscapePreview() {
    NoteMarkTheme {
        LoginScreenRoot(
            onGoToRegister = {},
            onGoToHome = {}
        )
    }
}
