package com.clarkelamothe.notemark.feature_auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.feature_auth.presentation.component.AuthHeader
import com.clarkelamothe.notemark.feature_auth.presentation.component.LoginForm

@Composable
fun LoginScreenPortrait(
    modifier: Modifier = Modifier,
    headerAlignment: TextAlign = TextAlign.Start,
    email: String = "",
    isEmailError: Boolean = false,
    onEmailChange: (String) -> Unit,
    password: String = "",
    isPasswordError: Boolean = false,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    canLogin: Boolean = false,
    onRegister: () -> Unit,
    isLoading: Boolean
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
            title = "Login",
            subtitle = "Capture your thoughts and ideas."
        )
        LoginForm(
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth(),
            email = email,
            password = password,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onLogin = onLogin,
            onRegister = onRegister,
            canLogin = canLogin,
            isLoading = isLoading,
            isEmailError = isEmailError,
            isPasswordError = isPasswordError
        )
    }
}
