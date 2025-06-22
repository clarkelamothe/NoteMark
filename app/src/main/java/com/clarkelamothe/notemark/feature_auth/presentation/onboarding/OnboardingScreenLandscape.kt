package com.clarkelamothe.notemark.feature_auth.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.R
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme

@Composable
fun OnboardingScreenLandscape(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onGetStartedClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0EAFF))
    ) {
        Image(
            painter = painterResource(R.drawable.bg_landing),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 20.dp)
                .align(Alignment.CenterStart),
            contentScale = ContentScale.Inside
        )

        OnboardingSheet(
            modifier = modifier.align(Alignment.CenterEnd),
            onLogin = onLoginClick,
            onGetStarted = onGetStartedClick,
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
