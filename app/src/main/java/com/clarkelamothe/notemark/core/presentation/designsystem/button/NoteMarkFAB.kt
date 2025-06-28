package com.clarkelamothe.notemark.core.presentation.designsystem.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.R
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme

@Composable
fun NoteMarkFAB(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF58A1F8), Color(0xFF5A4CF7)
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(12.dp),
        onClick = onClick
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_plus),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Preview
@Composable
private fun NoteMarkFABPreview() {
    NoteMarkTheme {
        NoteMarkFAB(
            onClick = {}
        )
    }
}
