package com.clarkelamothe.notemark.feature_auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.R
import com.clarkelamothe.notemark.core.presentation.designsystem.button.NoteMarkButton
import com.clarkelamothe.notemark.core.presentation.designsystem.input.NoteMarkInputTextField
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme

@Composable
fun RegisterForm(
    modifier: Modifier = Modifier,
    username: String,
    email: String,
    password: String,
    repeatPassword: String,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onRegister: () -> Unit
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
            isError = false,
            keyboardType = KeyboardType.Text,
            value = username,
            onValueChange = onUsernameChange
        )

        NoteMarkInputTextField(
            label = "Email",
            placeholder = "john.doe@example.com",
            isError = false,
            keyboardType = KeyboardType.Email,
            value = email,
            onValueChange = onEmailChange
        )

        NoteMarkInputTextField(
            label = "Password",
            placeholder = "Password",
            isError = false,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            value = password,
            onValueChange = onPasswordChange,
            trailingIcon = {
                IconButton(
                    onClick = {
                        revealPassword = !revealPassword
                    }
                ) {
                    if (revealPassword) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_eye_off),
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_eye),
                            contentDescription = null
                        )
                    }
                }
            }
        )

        NoteMarkInputTextField(
            label = "Repeat password",
            placeholder = "Password",
            isError = false,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            value = repeatPassword,
            onValueChange = onRepeatPasswordChange,
            trailingIcon = {
                IconButton(
                    onClick = { revealRepeatPassword = !revealRepeatPassword }
                ) {
                    if (revealRepeatPassword) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_eye_off),
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_eye),
                            contentDescription = null
                        )
                    }
                }
            }
        )

        NoteMarkButton(
            modifier = Modifier.fillMaxWidth(),
            label = "Create account",
            onClick = onRegister
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterFormPreview() {
    NoteMarkTheme {
        RegisterForm(
            email = "",
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            onRegister = {},
            username = "",
            repeatPassword = "",
            onUsernameChange = {},
            onRepeatPasswordChange = {}
        )
    }
}
