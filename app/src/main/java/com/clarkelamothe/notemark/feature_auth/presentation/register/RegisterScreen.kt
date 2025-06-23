package com.clarkelamothe.notemark.feature_auth.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.core.presentation.local.LocalOrientation
import com.clarkelamothe.notemark.core.presentation.local.Orientation
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme

@Composable
fun RegisterScreenRoot() {
    val orientation = LocalOrientation.current

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        when (orientation) {
            Orientation.PHONE_PORTRAIT -> RegisterScreenPortrait(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize(),
                username = "",
                email = "",
                password = "",
                repeatPassword = "",
                onPasswordChange = {},
                onRepeatPasswordChange = {},
                onUsernameChange = {},
                onEmailChange = {},
                onRegister = {},
            )

            Orientation.PHONE_LANDSCAPE -> RegisterScreenLandscape(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize(),
                username = "",
                email = "",
                password = "",
                repeatPassword = "",
                onPasswordChange = {},
                onRepeatPasswordChange = {},
                onUsernameChange = { },
                onEmailChange = { },
                onRegister = { }

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
                onRegister = {},
                username = "",
                email = "",
                password = "",
                repeatPassword = "",
                onPasswordChange = {},
                onRepeatPasswordChange = {},
                onUsernameChange = {},
                onEmailChange = {},
            )

            Orientation.TABLET_LANDSCAPE -> RegisterScreenLandscape(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding()),
                onPasswordChange = {},
                onEmailChange = {},
                username = "",
                email = "",
                password = "",
                repeatPassword = "",
                onRepeatPasswordChange = {},
                onUsernameChange = {},
                onRegister = {},
            )

            Orientation.DESKTOP, null -> {}
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    NoteMarkTheme {
        RegisterScreenRoot()
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun RegisterScreenLandscapetPreview() {
    NoteMarkTheme {
        RegisterScreenRoot()
    }
}
