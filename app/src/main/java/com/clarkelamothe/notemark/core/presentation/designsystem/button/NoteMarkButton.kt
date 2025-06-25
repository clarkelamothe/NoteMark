package com.clarkelamothe.notemark.core.presentation.designsystem.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.R
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme

@Composable
fun NoteMarkButton(
    modifier: Modifier = Modifier,
    label: String,
    enabled: Boolean = true,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        shape = RoundedCornerShape(8.dp),
        enabled = enabled,
        contentPadding = PaddingValues(
            horizontal = 10.dp,
            vertical = 6.dp
        )
    ) {
        leadingIcon()
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall
        )
        trailingIcon()
    }
}

@Composable
fun NoteMarkOutlinedButton(
    modifier: Modifier = Modifier,
    label: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        enabled = enabled,
        contentPadding = PaddingValues(
            horizontal = 10.dp,
            vertical = 6.dp
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NoteMarkButtonPreview() {
    NoteMarkTheme {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            NoteMarkButton(
                label = "Label",
                enabled = true,
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_copy),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(8.dp))
                },
                onClick = {}
            )

            NoteMarkButton(
                label = "Label",
                enabled = false,
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_copy),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(8.dp))
                },
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NoteMarkOutlinedButtonPreview() {
    NoteMarkTheme {
        NoteMarkOutlinedButton(
            label = "Label",
            onClick = {}
        )
    }
}
