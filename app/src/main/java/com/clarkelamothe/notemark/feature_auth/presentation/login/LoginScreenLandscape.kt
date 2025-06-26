package com.clarkelamothe.notemark.feature_auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme
import com.clarkelamothe.notemark.feature_auth.presentation.component.AuthHeader
import com.clarkelamothe.notemark.feature_auth.presentation.component.LoginForm

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoginScreenLandscape(
    modifier: Modifier = Modifier,
    email: String = "",
    password: String = "",
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onLogin: () -> Unit,
    canLogin: Boolean = false,
    onRegister: () -> Unit,
    isLoading: Boolean = false,
    isEmailError: Boolean = false,
    isPasswordError: Boolean = false
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
                title = "Login",
                subtitle = "Capture your thoughts and ideas."
            )
        }
        Column(
            modifier = Modifier
                .weight(0.5f)
                .navigationBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginForm(
                email = email,
                password = password,
                canLogin = canLogin,
                onEmailChange = onEmailChange,
                onPasswordChange = onPasswordChange,
                onLogin = onLogin,
                onRegister = onRegister,
                isLoading = isLoading,
                isEmailError = isEmailError,
                isPasswordError = isPasswordError
            )
            Spacer(Modifier.height(46.dp))
        }
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun LoginScreenLandscapePreview() {
    NoteMarkTheme {
        LoginScreenLandscape(
            onPasswordChange = {},
            onEmailChange = {},
            onLogin = {},
            onRegister = {}
        )
    }
}
