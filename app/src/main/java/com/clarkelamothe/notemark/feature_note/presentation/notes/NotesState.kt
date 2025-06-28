package com.clarkelamothe.notemark.feature_note.presentation.notes

import com.clarkelamothe.notemark.feature_note.domain.NoteBM

data class NotesState(
    val username: String = "",
    val notes: List<NoteBM> = emptyList(),
    val showEmptyState: Boolean = false
)
