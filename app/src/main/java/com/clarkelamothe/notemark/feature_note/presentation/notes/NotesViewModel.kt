package com.clarkelamothe.notemark.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotesViewModel : ViewModel() {
    private val _state = MutableStateFlow(NotesState())
    val state = _state.asStateFlow()

    fun onAction(action: NotesAction) {
        when (action) {
            NotesAction.OnCreateNote -> TODO()
            NotesAction.OnDeleteNote -> TODO()
            NotesAction.OnNoteClick -> TODO()
            else -> {} /* No-op */
        }
    }
}
