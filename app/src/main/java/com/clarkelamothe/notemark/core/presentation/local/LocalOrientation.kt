package com.clarkelamothe.notemark.core.presentation.local

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.window.core.layout.WindowSizeClass

enum class Orientation {
    PHONE_PORTRAIT, PHONE_LANDSCAPE,
    TABLET_PORTRAIT, TABLET_LANDSCAPE,
    DESKTOP
}

val LocalOrientation = staticCompositionLocalOf<Orientation?> { null }

@Composable
fun ProvideOrientation(content: @Composable () -> Unit) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    val orientation = when {
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
            when {
                windowSizeClass.isHeightAtLeastBreakpoint(WindowSizeClass.HEIGHT_DP_EXPANDED_LOWER_BOUND) -> Orientation.DESKTOP
                windowSizeClass.isHeightAtLeastBreakpoint(WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND) -> Orientation.TABLET_LANDSCAPE
                else -> Orientation.PHONE_LANDSCAPE
            }
        }

        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
            when {
                windowSizeClass.isHeightAtLeastBreakpoint(WindowSizeClass.HEIGHT_DP_EXPANDED_LOWER_BOUND) -> Orientation.TABLET_PORTRAIT
                windowSizeClass.isHeightAtLeastBreakpoint(WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND) -> Orientation.TABLET_LANDSCAPE
                else -> Orientation.PHONE_LANDSCAPE
            }
        }

        windowSizeClass.isHeightAtLeastBreakpoint(WindowSizeClass.HEIGHT_DP_EXPANDED_LOWER_BOUND) -> Orientation.PHONE_PORTRAIT
        else -> Orientation.PHONE_PORTRAIT
    }

    CompositionLocalProvider(LocalOrientation provides orientation) {
        content()
    }
}
