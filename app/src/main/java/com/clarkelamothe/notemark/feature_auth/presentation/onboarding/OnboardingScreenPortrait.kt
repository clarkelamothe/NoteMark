package com.clarkelamothe.notemark.feature_auth.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.clarkelamothe.notemark.R

@Composable
fun OnboardingScreenPortrait(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onGetStartedClick: () -> Unit,
    header: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color(0xFFE0EAFF))
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.bg_landing),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight(0.75f)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.FillHeight
        )

        OnboardingSheet(
            modifier = modifier
                .align(Alignment.BottomCenter),
            onGoToLogin = onLoginClick,
            onGetStarted = onGetStartedClick,
            header = header
        )
    }
}
