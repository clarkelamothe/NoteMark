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
fun RegisterForm(
    modifier: Modifier = Modifier,
    username: String,
    email: String,
    password: String,
    repeatPassword: String,
    canRegister: Boolean = false,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onRegister: () -> Unit,
    onGoToLogin: () -> Unit,
    isEmailError: Boolean = false,
    isPasswordError: Boolean = false,
    isRepeatPasswordError: Boolean = false,
    isUsernameError: Boolean = false
) {
    var revealPassword by rememberSaveable {
        mutableStateOf(false)
    }

    var revealRepeatPassword by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NoteMarkInputTextField(
            label = "Username",
            placeholder = "John.doe",
            isError = isUsernameError,
            keyboardType = KeyboardType.Text,
            value = username,
            onValueChange = onUsernameChange,
            supportingText = "Username must be at least 3 characters"
        )

        NoteMarkInputTextField(
            label = "Email",
            placeholder = "john.doe@example.com",
            isError = isEmailError,
            keyboardType = KeyboardType.Email,
            value = email,
            onValueChange = onEmailChange,
            supportingText = "Invalid email provided"
        )

        NoteMarkInputTextField(
            label = "Password",
            placeholder = "Password",
            isError = isPasswordError,
            keyboardType = KeyboardType.Password,
            visualTransformation = passwordVisualTransformation(revealPassword),
            value = password,
            onValueChange = onPasswordChange,
            trailingIcon = {
                EyeIconToggle(
                    revealPassword
                ) {
                    revealPassword = !revealPassword
                }
            },
            supportingText = "Password must be at least ${UserDataValidator.MIN_PASSWORD_LENGTH} characters and include a number or symbol."
        )

        NoteMarkInputTextField(
            label = "Repeat password",
            placeholder = "Password",
            isError = isRepeatPasswordError,
            keyboardType = KeyboardType.Password,
            visualTransformation = passwordVisualTransformation(revealRepeatPassword),
            value = repeatPassword,
            onValueChange = onRepeatPasswordChange,
            trailingIcon = {
                EyeIconToggle(
                    revealRepeatPassword
                ) {
                    revealRepeatPassword = !revealRepeatPassword
                }
            },
            imeAction = ImeAction.Done,
            keyboardActions = KeyboardActions(
                onDone = {
                    if (canRegister) onRegister()
                }
            ),
            supportingText = "Passwords do not match"
        )

        NoteMarkButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = canRegister,
            label = "Create account",
            onClick = onRegister
        )
        Spacer(Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onGoToLogin()
                },
            text = "Already have an account?",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterFormPreview() {
    NoteMarkTheme {
        RegisterForm(
            username = "",
            email = "",
            password = "",
            repeatPassword = "",
            onUsernameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onRepeatPasswordChange = {},
            onRegister = {},
            onGoToLogin = {},
        )
    }
}
