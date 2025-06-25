package com.clarkelamothe.notemark.feature_auth.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun passwordVisualTransformation(revealPassword: Boolean) =
    if (revealPassword) VisualTransformation.None else PasswordVisualTransformation()
