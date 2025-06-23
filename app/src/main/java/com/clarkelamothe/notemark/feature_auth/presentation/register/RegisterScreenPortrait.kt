package com.clarkelamothe.notemark.feature_auth.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.feature_auth.presentation.component.AuthHeader

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
    onRegister: () -> Unit
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
        Spacer(Modifier.height(40.dp))
        RegisterForm(
            modifier = Modifier.fillMaxWidth(),
            username = username,
            email = email,
            password = password,
            repeatPassword = repeatPassword,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange,
            onRegister = onRegister,
            onUsernameChange = onUsernameChange,
            onRepeatPasswordChange = onRepeatPasswordChange
        )
        Spacer(Modifier.height(24.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            text = "Already have an account?",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
