@file:OptIn(ExperimentalMaterial3Api::class)

package com.clarkelamothe.notemark.feature_note.presentation.component

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    date: String,
    title: String,
    description: String,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClickLabel = "Note Details",
                onClick = onClick,
                onLongClickLabel = "Delete Note",
                onLongClick = onLongClick
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = modifier.padding(16.dp)
        ) {
            Text(
                text = date,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = description
            )
        }
    }
}

@Preview
@Composable
private fun NoteCardPreview() {
    NoteMarkTheme {
        NoteCard(
            date = "19 APR",
            title = "Title",
            description = "Augue non mauris ante viverra ut arcu sed ut lectus interdum morbi sed l"
        )
    }
}
