package com.clarkelamothe.notemark.feature_note.presentation.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.feature_note.domain.NoteBM
import com.clarkelamothe.notemark.feature_note.presentation.component.NoteCard

@Composable
fun NotesScreenContent(
    modifier: Modifier = Modifier,
    notes: List<NoteBM>,
    contentPadding: PaddingValues,
    columns: StaggeredGridCells = StaggeredGridCells.Fixed(3),
    onClickNote: () -> Unit,
    onLongClickNote: () -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        columns = columns
    ) {
        items(
            items = notes,
            key = { it.id }
        ) {
            NoteCard(
                date = it.date,
                title = it.title,
                description = it.description,
                onClick = onClickNote,
                onLongClick = onLongClickNote
            )
        }
    }
}
