package com.clarkelamothe.notemark.feature_auth.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.core.presentation.local.LocalOrientation
import com.clarkelamothe.notemark.core.presentation.local.Orientation
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme
import com.clarkelamothe.notemark.feature_auth.presentation.component.AuthHeader

@Composable
fun OnboardingScreenRoot(
    onGoToLogin: () -> Unit,
    onGetStartedClick: () -> Unit
) {
    val orientation = LocalOrientation.current

    when (orientation) {
        Orientation.PHONE_PORTRAIT -> OnboardingScreenPortrait(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerLowest,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp
                    )
                )
                .padding(
                    vertical = 32.dp,
                    horizontal = 16.dp
                ),
            onLoginClick = onGoToLogin,
            onGetStartedClick = onGetStartedClick,
            header = {
                AuthHeader(
                    title = "Your Own Collection of Notes",
                    subtitle = "Capture your thoughts and ideas."
                )
            }
        )

        Orientation.PHONE_LANDSCAPE -> OnboardingScreenLandscape(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerLowest,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        bottomStart = 20.dp
                    )
                )
                .padding(
                    top = 40.dp,
                    start = 60.dp,
                    end = 40.dp,
                    bottom = 40.dp
                )
                .wrapContentHeight()
                .fillMaxWidth(0.5f),
            onLoginClick = onGoToLogin,
            onGetStartedClick = onGetStartedClick
        )

        Orientation.TABLET_PORTRAIT -> {
            OnboardingScreenPortrait(
                modifier = Modifier
                    .padding(horizontal = 60.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainerLowest,
                        shape = RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp
                        )
                    )
                    .padding(
                        top = 48.dp,
                        start = 48.dp,
                        end = 48.dp,
                        bottom = 32.dp
                    ),
                onLoginClick = onGoToLogin,
                onGetStartedClick = onGetStartedClick,
                header = {
                    AuthHeader(
                        title = "Your Own Collection of Notes",
                        subtitle = "Capture your thoughts and ideas.",
                        alignment = TextAlign.Center
                    )
                }
            )
        }

        Orientation.TABLET_LANDSCAPE -> {
            OnboardingScreenLandscape(
                Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainerLowest,
                        shape = RoundedCornerShape(
                            topStart = 24.dp,
                            bottomStart = 24.dp
                        )
                    )
                    .padding(
                        top = 32.dp,
                        start = 60.dp,
                        end = 48.dp,
                        bottom = 32.dp
                    )
                    .wrapContentHeight()
                    .fillMaxWidth(0.5f),
                onLoginClick = onGoToLogin,
                onGetStartedClick = onGetStartedClick
            )
        }

        Orientation.DESKTOP, null -> { /* No-Op */
        }
    }
}

@PreviewScreenSizes
@Composable
private fun OnboardingScreenPreview() {
    NoteMarkTheme {
        OnboardingScreenRoot(
            onGoToLogin = {},
            onGetStartedClick = {}
        )
    }
}
