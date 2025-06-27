@file:OptIn(ExperimentalMaterial3Api::class)

package com.clarkelamothe.notemark.core.presentation.designsystem.appbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.clarkelamothe.notemark.core.presentation.designsystem.icon.ProfileIcon
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme

@Composable
fun NoteMarkTopBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable (RowScope.() -> Unit) = {}
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        navigationIcon = navigationIcon,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        },
        actions = actions
    )
}

@Preview
@Composable
private fun NoteMarkTopBarPreview() {
    NoteMarkTheme {
        NoteMarkTopBar(
            title = "NoteMark",
            actions = {
                ProfileIcon(
                    initials = "PL"
                ) {

                }
            }
        )
    }
}