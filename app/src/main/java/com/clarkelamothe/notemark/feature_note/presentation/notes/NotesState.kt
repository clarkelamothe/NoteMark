package com.clarkelamothe.notemark.feature_note.presentation.notes

data class NotesState(
    val username: String = "",
    val notes: List<Any> = emptyList(),
    val showEmptyState: Boolean = false
)
