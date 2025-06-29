package com.clarkelamothe.notemark.feature_note.presentation.note

sealed interface NoteEvent {
    data object ShowDialog : NoteEvent
    data object DismissNote : NoteEvent
}
