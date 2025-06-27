package com.clarkelamothe.notemark.feature_note.presentation.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NoteMarkDialog(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        icon = null,
        title = {
            Text(text = title)
        },
        text = {
            Text(text = text)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }
            ) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}

@Preview
@Composable
private fun NoteMarkDialogPreview() {
    NoteMarkDialog(
        title = "Delete Note",
        text = "Are you sure you want to delete this note?",
        onConfirm = {},
        onDismissRequest = {}
    )
}
