package com.clarkelamothe.notemark.feature_auth.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.feature_auth.presentation.component.AuthHeader
import com.clarkelamothe.notemark.feature_auth.presentation.component.RegisterForm

@Composable
fun RegisterScreenLandscape(
    modifier: Modifier = Modifier,
    username: String,
    usernameError: Boolean = false,
    email: String,
    emailError: Boolean = false,
    password: String,
    passwordError: Boolean = false,
    repeatPassword: String,
    repeatPasswordError: Boolean = false,
    canRegister: Boolean = false,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onRegister: () -> Unit,
    onGoToLogin: () -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerLowest,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
            )
            .padding(
                top = 32.dp,
                start = 60.dp,
                end = 40.dp
            )
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.weight(0.5f)
        ) {
            AuthHeader(
                title = "Create account",
                subtitle = "Capture your thoughts and ideas."
            )
        }
        Column(
            modifier = Modifier
                .weight(0.5f)
                .statusBarsPadding()
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RegisterForm(
                username = username,
                email = email,
                password = password,
                repeatPassword = repeatPassword,
                canRegister = canRegister,
                onUsernameChange = onUsernameChange,
                onEmailChange = onEmailChange,
                onPasswordChange = onPasswordChange,
                onRepeatPasswordChange = onRepeatPasswordChange,
                onRegister = onRegister,
                onGoToLogin = onGoToLogin,
                isEmailError = emailError,
                isPasswordError = passwordError,
                isUsernameError = usernameError,
                isRepeatPasswordError = repeatPasswordError
            )
            Spacer(Modifier.height(46.dp))
        }
    }
}
