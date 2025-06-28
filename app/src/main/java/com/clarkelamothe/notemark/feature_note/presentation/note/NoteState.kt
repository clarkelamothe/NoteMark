package com.clarkelamothe.notemark.feature_note.presentation.note

data class NoteState(
    val title: String = "",
    val description: String = "",
    val canSaveNote: Boolean = false
)
