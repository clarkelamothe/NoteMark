package com.clarkelamothe.notemark.feature_note.presentation.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.clarkelamothe.notemark.feature_note.presentation.component.NoteCard

@Composable
fun NotesScreenPortrait(
    modifier: Modifier = Modifier,
    onClickNote: () -> Unit,
    onLongClickNote: () -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        columns = StaggeredGridCells.Fixed(2)
    ) {
        (1..10).map { nm ->
            item {
                NoteCard(
                    date = "17 apr",
                    title = "Title",
                    description = "description",
                    onClick = onClickNote,
                    onLongClick = onLongClickNote
                )
            }
        }
    }
}