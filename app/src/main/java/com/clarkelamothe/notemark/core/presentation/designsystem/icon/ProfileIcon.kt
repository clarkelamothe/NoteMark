package com.clarkelamothe.notemark.core.presentation.designsystem.icon

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme

@Composable
fun ProfileIcon(
    modifier: Modifier = Modifier,
    initials: String,
    onClick: () -> Unit
) {
    FilledIconButton(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Text(
            text = initials,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview
@Composable
private fun ProfileIconPreview() {
    NoteMarkTheme {
        ProfileIcon(
            initials = "CL",
            onClick = {}
        )
    }
}