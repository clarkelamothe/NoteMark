package com.clarkelamothe.notemark.feature_auth.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AuthHeader(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    alignment: TextAlign = TextAlign.Start
) {
    Text(
        modifier = modifier,
        text = title,
        textAlign = alignment,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
    Text(
        modifier = modifier,
        text = subtitle,
        textAlign = alignment,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
