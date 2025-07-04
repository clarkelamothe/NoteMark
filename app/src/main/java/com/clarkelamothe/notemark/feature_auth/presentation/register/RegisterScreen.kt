package com.clarkelamothe.notemark.feature_auth.presentation.register

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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
fun RegisterScreenRoot(
    viewModel: AuthViewModel = koinViewModel(),
    onGoToLogin: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val orientation = LocalOrientation.current
    val keyboard = LocalSoftwareKeyboardController.current
    val snackbarHostState = remember { SnackbarHostState() }
    val registerState by viewModel.registerState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.registerEvents) { event ->
        keyboard?.hide()

        when (event) {
            RegisterEvent.OnRegisterError -> scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Error registering!"
                )
            }

            RegisterEvent.OnRegisterSuccess -> onGoToLogin()
        }
    }

    RegisterScreen(
        orientation,
        registerState,
        snackbarHostState
    ) { action ->
        when (action) {
            RegisterAction.OnLoginClick -> onGoToLogin()
            else -> {} /* No-op */
        }
        viewModel.onAction(action)
        snackbarHostState.currentSnackbarData?.dismiss()
    }
}

@Composable
fun RegisterScreen(
    orientation: Orientation?,
    state: RegisterState,
    snackbarHostState: SnackbarHostState,
    onAction: (RegisterAction) -> Unit
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
            Orientation.PHONE_PORTRAIT -> RegisterScreenPortrait(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize(),
                username = state.username,
                email = state.email,
                password = state.password,
                repeatPassword = state.repeatPassword,
                canRegister = state.canRegister,
                onPasswordChange = { password ->
                    onAction(RegisterAction.OnInputPassword(password))
                },
                onRepeatPasswordChange = { password ->
                    onAction(RegisterAction.OnRepeatPassword(password))
                },
                onUsernameChange = { username ->
                    onAction(RegisterAction.OnInputUsername(username))
                },
                onEmailChange = { email ->
                    onAction(RegisterAction.OnInputEmail(email))
                },
                onRegister = {
                    onAction(RegisterAction.OnRegisterClick)
                },
                onGoToLogin = {
                    onAction(RegisterAction.OnLoginClick)
                },
                usernameError = state.usernameError,
                emailError = state.emailError,
                passwordError = state.passwordError,
                repeatPasswordError = state.repeatPasswordError,
                isLoading = state.isRegistering
            )

            Orientation.PHONE_LANDSCAPE -> RegisterScreenLandscape(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize(),
                username = state.username,
                email = state.email,
                password = state.password,
                repeatPassword = state.repeatPassword,
                onPasswordChange = { password ->
                    onAction(RegisterAction.OnInputPassword(password))
                },
                onRepeatPasswordChange = { password ->
                    onAction(RegisterAction.OnRepeatPassword(password))
                },
                onUsernameChange = { username ->
                    onAction(RegisterAction.OnInputUsername(username))
                },
                onEmailChange = { email ->
                    onAction(RegisterAction.OnInputEmail(email))
                },
                onRegister = {
                    onAction(RegisterAction.OnRegisterClick)
                },
                onGoToLogin = {
                    onAction(RegisterAction.OnLoginClick)
                },
                emailError = state.emailError,
                usernameError = state.usernameError,
                passwordError = state.passwordError,
                repeatPasswordError = state.repeatPasswordError
            )

            Orientation.TABLET_PORTRAIT -> RegisterScreenPortrait(
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
                username = state.username,
                email = state.email,
                password = state.password,
                repeatPassword = state.repeatPassword,
                onPasswordChange = { password ->
                    onAction(RegisterAction.OnInputPassword(password))
                },
                onRepeatPasswordChange = { password ->
                    onAction(RegisterAction.OnRepeatPassword(password))
                },
                onUsernameChange = { username ->
                    onAction(RegisterAction.OnInputUsername(username))
                },
                onEmailChange = { email ->
                    onAction(RegisterAction.OnInputEmail(email))
                },
                onRegister = {
                    onAction(RegisterAction.OnRegisterClick)
                },
                onGoToLogin = {
                    onAction(RegisterAction.OnLoginClick)
                },
                emailError = state.emailError,
                usernameError = state.usernameError,
                passwordError = state.passwordError,
                repeatPasswordError = state.repeatPasswordError
            )

            Orientation.TABLET_LANDSCAPE -> RegisterScreenLandscape(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding()),
                username = state.username,
                email = state.email,
                password = state.password,
                repeatPassword = state.repeatPassword,
                onPasswordChange = { password ->
                    onAction(RegisterAction.OnInputPassword(password))
                },
                onRepeatPasswordChange = { password ->
                    onAction(RegisterAction.OnRepeatPassword(password))
                },
                onUsernameChange = { username ->
                    onAction(RegisterAction.OnInputUsername(username))
                },
                onEmailChange = { email ->
                    onAction(RegisterAction.OnInputEmail(email))
                },
                onRegister = {
                    onAction(RegisterAction.OnRegisterClick)
                },
                onGoToLogin = {
                    onAction(RegisterAction.OnLoginClick)
                },
                emailError = state.emailError,
                usernameError = state.usernameError,
                passwordError = state.passwordError,
                repeatPasswordError = state.repeatPasswordError
            )

            Orientation.DESKTOP, null -> {}
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    NoteMarkTheme {
        RegisterScreenRoot(
            onGoToLogin = {}
        )
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun RegisterScreenLandscapetPreview() {
    NoteMarkTheme {
        RegisterScreenRoot(
            onGoToLogin = {}
        )
    }
}
