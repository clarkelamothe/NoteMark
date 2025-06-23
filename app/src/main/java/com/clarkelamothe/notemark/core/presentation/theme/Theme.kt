package com.clarkelamothe.notemark.core.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.clarkelamothe.notemark.core.presentation.local.ProvideOrientation

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    onPrimary = White,
    surface = LightGray,
    onSurface = Black,
    surfaceContainerLowest = White,
    onSurfaceVariant = Gray,
    error = Red
)

@Composable
fun NoteMarkTheme(
    content: @Composable () -> Unit
) {
    ProvideOrientation {
        MaterialTheme(
            colorScheme = LightColorScheme,
            typography = Typography,
            content = content
        )
    }
}
