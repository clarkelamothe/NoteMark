package com.clarkelamothe.notemark.feature_auth.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.feature_auth.presentation.component.AuthHeader
import com.clarkelamothe.notemark.feature_auth.presentation.component.RegisterForm

@Composable
fun RegisterScreenPortrait(
    modifier: Modifier = Modifier,
    username: String,
    email: String,
    password: String,
    repeatPassword: String,
    headerAlignment: TextAlign = TextAlign.Start,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onRegister: () -> Unit,
    onGoToLogin: () -> Unit
) {
    Column(
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
                start = 16.dp,
                end = 16.dp
            )
    ) {
        AuthHeader(
            modifier = Modifier.fillMaxWidth(),
            alignment = headerAlignment,
            title = "Create account",
            subtitle = "Capture your thoughts and ideas."
        )
        RegisterForm(
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth()
                .imePadding()
                .verticalScroll(rememberScrollState()),
            username = username,
            email = email,
            password = password,
            repeatPassword = repeatPassword,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onRegister = onRegister,
            onUsernameChange = onUsernameChange,
            onRepeatPasswordChange = onRepeatPasswordChange,
            onGoToLogin = onGoToLogin
        )
    }
}
