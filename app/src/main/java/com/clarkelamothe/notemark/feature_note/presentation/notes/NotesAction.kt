package com.clarkelamothe.notemark.feature_note.presentation.notes

sealed interface NotesAction {
    data object OnNoteClick : NotesAction
    data object OnLongClick : NotesAction
    data object OnDeleteNote : NotesAction
    data object OnCreateNote : NotesAction
}
