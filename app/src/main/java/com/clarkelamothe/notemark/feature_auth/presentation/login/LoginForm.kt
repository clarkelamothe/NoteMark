package com.clarkelamothe.notemark.feature_auth.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.R
import com.clarkelamothe.notemark.core.presentation.designsystem.button.NoteMarkButton
import com.clarkelamothe.notemark.core.presentation.designsystem.input.NoteMarkInputTextField
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    onRegister: () -> Unit
) {
    var revealPassword by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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

        NoteMarkButton(
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
            onRegister = {}
        )
    }
}
