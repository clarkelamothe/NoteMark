@file:OptIn(ExperimentalMaterial3Api::class)

package com.clarkelamothe.notemark.feature_note.presentation.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.clarkelamothe.notemark.core.presentation.designsystem.appbar.NoteMarkTopBar
import com.clarkelamothe.notemark.core.presentation.designsystem.icon.ProfileIcon
import com.clarkelamothe.notemark.core.presentation.local.LocalOrientation
import com.clarkelamothe.notemark.core.presentation.local.Orientation
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme
import com.clarkelamothe.notemark.feature_note.presentation.component.NoteMarkDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotesScreenRoot(
    viewModel: NotesViewModel = koinViewModel(),
    onGoToNote: () -> Unit
) {
    val orientation = LocalOrientation.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showDialog by rememberSaveable { mutableStateOf(false) }

    NotesScreen(
        state = state,
        orientation = orientation,
        onAction = { action ->
            when (action) {
                NotesAction.OnCreateNote -> {}
                NotesAction.OnLongClickNote -> showDialog = true
                NotesAction.OnClickNote -> onGoToNote()
                else -> {} /* No-op */
            }
            viewModel.onAction(action)
        }
    )

    NoteMarkDialog(
        show = showDialog,
        title = "Delete Note",
        text = "Are you sure you want to delete this note? This action cannot be undone.",
        onConfirm = { viewModel.onAction(NotesAction.OnDeleteNote) },
        onDismissRequest = { showDialog = false }
    )
}

@Composable
fun NotesScreen(
    orientation: Orientation?,
    state: NotesState,
    onAction: (NotesAction) -> Unit
) {
    Scaffold(
        topBar = {
            NoteMarkTopBar(
                title = "NoteMark",
                actions = {
                    ProfileIcon(initials = "PL") {}
                }
            )
        },
        floatingActionButton = {

        }
    ) {
        when (orientation) {
            Orientation.PHONE_PORTRAIT, Orientation.TABLET_PORTRAIT -> NotesScreenPortrait(
                modifier = Modifier.padding(top = it.calculateTopPadding()),
                onClickNote = {
                    onAction(NotesAction.OnClickNote)
                },
                onLongClickNote = {
                    onAction(NotesAction.OnLongClickNote)
                }
            )

            Orientation.PHONE_LANDSCAPE, Orientation.TABLET_LANDSCAPE -> {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.padding(top = it.calculateTopPadding()),
                    contentPadding = PaddingValues(16.dp),
                    verticalItemSpacing = 16.dp,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    columns = StaggeredGridCells.Fixed(3)
                ) {
                    (1..10).map { nm ->
                        item {
                            Text(
                                text = nm.toString(),
                                modifier = Modifier
                                    .background(Color.LightGray)
                            )
                        }
                    }
                }
            }

            Orientation.DESKTOP, null -> TODO()
        }
    }
}

@Preview
@Composable
private fun NotesScreenPreview() {
    NoteMarkTheme {
        NotesScreenRoot(
            onGoToNote = {}
        )
    }
}
