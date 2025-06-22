package com.clarkelamothe.notemark.feature_auth.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.core.presentation.designsystem.button.NoteMarkButton
import com.clarkelamothe.notemark.core.presentation.designsystem.button.NoteMarkOutlinedButton
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme

@Composable
fun OnboardingSheet(
    modifier: Modifier = Modifier,
    header: @Composable ColumnScope.() -> Unit,
    onGetStarted: () -> Unit,
    onLogin: () -> Unit
) {
    Column(
        modifier = modifier.wrapContentWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        header()
        Spacer(Modifier.height(40.dp))
        NoteMarkButton(
            label = "Get Started",
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onLogin
        )
        Spacer(Modifier.height(6.dp))
        NoteMarkOutlinedButton(
            label = "Login",
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onGetStarted
        )
    }
}

@Preview
@Composable
private fun OnboardingSheetPreview() {
    NoteMarkTheme {
        OnboardingSheet(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerLowest,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                )
                .padding(16.dp),
            onLogin = {},
            onGetStarted = {},
            header = {
                Text(
                    text = "Your Own Collection of Notes",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Capture your thoughts and ideas.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        )
    }
}
