package com.clarkelamothe.notemark.feature_note.presentation.notes

sealed interface NotesAction {
    data object OnClickNote : NotesAction
    data object OnLongClickNote : NotesAction
    data object OnDeleteNote : NotesAction
    data object OnCreateNote : NotesAction
}
