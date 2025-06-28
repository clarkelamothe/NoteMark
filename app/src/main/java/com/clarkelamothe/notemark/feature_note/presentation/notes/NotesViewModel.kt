package com.clarkelamothe.notemark.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clarkelamothe.notemark.feature_note.domain.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update

class NotesViewModel(
    repository: NoteRepository
) : ViewModel() {
    private val _state = MutableStateFlow(NotesState())
    val state = _state.asStateFlow()

    init {
        combine(
            repository.getNotes(),
            state
        ) { notes, s ->
            _state.update {
                it.copy(
                    showEmptyState = notes.isEmpty()
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onAction(action: NotesAction) {
        when (action) {
            NotesAction.OnCreateNote -> TODO()
            NotesAction.OnDeleteNote -> TODO()
            else -> {} /* No-op */
        }
    }
}
