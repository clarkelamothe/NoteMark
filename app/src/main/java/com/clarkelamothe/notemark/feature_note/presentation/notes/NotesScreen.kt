@file:OptIn(ExperimentalMaterial3Api::class)

package com.clarkelamothe.notemark.feature_note.presentation.notes

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.clarkelamothe.notemark.core.presentation.designsystem.appbar.NoteMarkTopBar
import com.clarkelamothe.notemark.core.presentation.designsystem.button.NoteMarkFAB
import com.clarkelamothe.notemark.core.presentation.designsystem.icon.ProfileIcon
import com.clarkelamothe.notemark.core.presentation.local.LocalOrientation
import com.clarkelamothe.notemark.core.presentation.local.Orientation
import com.clarkelamothe.notemark.core.presentation.theme.NoteMarkTheme
import com.clarkelamothe.notemark.feature_note.domain.NoteBM
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
                },
                titlePadding = when (orientation) {
                    Orientation.PHONE_PORTRAIT, Orientation.TABLET_PORTRAIT -> PaddingValues(0.dp)
                    Orientation.PHONE_LANDSCAPE, Orientation.TABLET_LANDSCAPE -> PaddingValues(start = 60.dp)
                    Orientation.DESKTOP, null -> TODO()
                }
            )
        },
        floatingActionButton = {
            NoteMarkFAB {
                onAction(NotesAction.OnCreateNote)
            }
        }
    ) {
        when (state.showEmptyState) {
            true -> {
                Text(
                    modifier = Modifier
                        .padding(top = it.calculateTopPadding() + 80.dp)
                        .fillMaxWidth(),
                    text = "You’ve got an empty board,\n" + "let’s place your first note on it!",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }

            false -> when (orientation) {
                Orientation.PHONE_PORTRAIT, Orientation.TABLET_PORTRAIT -> NotesScreenContent(
                    modifier = Modifier.padding(top = it.calculateTopPadding()),
                    onClickNote = {
                        onAction(NotesAction.OnClickNote)
                    },
                    onLongClickNote = {
                        onAction(NotesAction.OnLongClickNote)
                    },
                    notes = state.notes,
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp)
                )

                Orientation.PHONE_LANDSCAPE, Orientation.TABLET_LANDSCAPE -> NotesScreenContent(
                    modifier = Modifier.padding(top = it.calculateTopPadding()),
                    onClickNote = {
                        onAction(NotesAction.OnClickNote)
                    },
                    onLongClickNote = {
                        onAction(NotesAction.OnLongClickNote)
                    },
                    notes = state.notes,
                    columns = StaggeredGridCells.Fixed(3),
                    contentPadding = PaddingValues(start = 60.dp, top = 16.dp, end = 16.dp)
                )

                Orientation.DESKTOP, null -> TODO()
            }
        }
    }
}

@Preview
@Composable
private fun NotesScreenPortraitPreview() {
    NoteMarkTheme {
        NotesScreen(
            orientation = Orientation.PHONE_PORTRAIT,
            state = NotesState(
                notes = listOf(
                    NoteBM(
                        id = "1",
                        date = "19 Apr",
                        title = "Title",
                        description = "Description"
                    ),
                    NoteBM(
                        id = "2",
                        date = "19 Apr",
                        title = "Title",
                        description = "Description"
                    ),
                    NoteBM(
                        id = "3",
                        date = "19 Apr",
                        title = "Title",
                        description = "Description"
                    ),
                    NoteBM(
                        id = "4",
                        date = "19 Apr",
                        title = "Title",
                        description = "Description"
                    ),
                    NoteBM(
                        id = "5",
                        date = "19 Apr",
                        title = "Title",
                        description = "Description"
                    )
                )
            ),
            onAction = {}
        )
    }
}

@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun NotesScreenLandscapePreview() {
    NoteMarkTheme {
        NotesScreen(
            orientation = Orientation.PHONE_LANDSCAPE,
            state = NotesState(
                notes = listOf(
                    NoteBM(
                        id = "1",
                        date = "19 Apr",
                        title = "Title",
                        description = "Description"
                    ),
                    NoteBM(
                        id = "2",
                        date = "19 Apr",
                        title = "Title",
                        description = "Description"
                    ),
                    NoteBM(
                        id = "3",
                        date = "19 Apr",
                        title = "Title",
                        description = "Description"
                    ),
                    NoteBM(
                        id = "4",
                        date = "19 Apr",
                        title = "Title",
                        description = "Description"
                    ),
                    NoteBM(
                        id = "5",
                        date = "19 Apr",
                        title = "Title",
                        description = "Description"
                    )
                )
            ),
            onAction = {}
        )
    }
}
