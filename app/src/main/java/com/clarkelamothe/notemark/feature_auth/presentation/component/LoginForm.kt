package com.clarkelamothe.notemark.feature_auth.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.core.presentation.designsystem.button.NoteMarkButton
import com.clarkelamothe.notemark.core.presentation.designsystem.input.NoteMarkInputTextField
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme
import com.clarkelamothe.notemark.feature_auth.domain.UserDataValidator

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    email: String,
    isEmailError: Boolean = false,
    emailSupportingText: String = "Invalid email provided",
    passwordSupportingText: String = "Password must be at least ${UserDataValidator.MIN_PASSWORD_LENGTH} characters and include a number or symbol.",
    password: String,
    isPasswordError: Boolean = false,
    canLogin: Boolean = false,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    onRegister: () -> Unit,
    isLoading: Boolean = false
) {
    var revealPassword by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NoteMarkInputTextField(
            modifier = Modifier,
            label = "Email",
            placeholder = "john.doe@example.com",
            isError = isEmailError,
            keyboardType = KeyboardType.Email,
            value = email,
            imeAction = ImeAction.Next,
            onValueChange = onEmailChange,
            supportingText = emailSupportingText
        )

        NoteMarkInputTextField(
            label = "Password",
            placeholder = "Password",
            isError = isPasswordError,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            visualTransformation = passwordVisualTransformation(revealPassword),
            value = password,
            onValueChange = onPasswordChange,
            trailingIcon = {
                EyeIconToggle(
                    onClick = {
                        revealPassword = !revealPassword
                    },
                    isOn = revealPassword
                )
            },
            keyboardActions = KeyboardActions(
                onDone = { if (canLogin) onLogin() }
            ),
            supportingText = passwordSupportingText
        )

        NoteMarkButton(
            isLoading = isLoading,
            enabled = canLogin,
            modifier = Modifier.fillMaxWidth(),
            label = "Login",
            onClick = onLogin
        )
        Spacer(Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onRegister() },
            text = "Don’t have an account?",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginFormPreview() {
    NoteMarkTheme {
        LoginForm(
            email = "",
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            onLogin = {},
            onRegister = {},
            isLoading = false
        )
    }
}
