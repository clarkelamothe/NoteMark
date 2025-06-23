package com.clarkelamothe.notemark.feature_auth.presentation.login

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
fun LoginScreenRoot(
    onGoToRegister: () -> Unit
) {
    val orientation = LocalOrientation.current

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        when (orientation) {
            Orientation.PHONE_PORTRAIT -> LoginScreenPortrait(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize(),
                onRegister = onGoToRegister,
                onLogin = {}
            )

            Orientation.PHONE_LANDSCAPE -> LoginScreenLandscape(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding())
                    .fillMaxSize(),
                onEmailChange = {},
                onPasswordChange = {},
                onLogin = {},
                onRegister = onGoToRegister
            )

            Orientation.TABLET_PORTRAIT -> LoginScreenPortrait(
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
                onRegister = onGoToRegister,
                onLogin = {}
            )

            Orientation.TABLET_LANDSCAPE -> LoginScreenLandscape(
                modifier = Modifier
                    .padding(top = it.calculateTopPadding()),
                onPasswordChange = {},
                onEmailChange = {},
                onLogin = {},
                onRegister = onGoToRegister
            )

            Orientation.DESKTOP, null -> {}
        }
    }

}

@Preview
@Composable
private fun LoginScreenPreview() {
    NoteMarkTheme {
        LoginScreenRoot(
            onGoToRegister = {}
        )
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun LoginScreenLandscapePreview() {
    NoteMarkTheme {
        LoginScreenRoot(
            onGoToRegister = {}
        )
    }
}
