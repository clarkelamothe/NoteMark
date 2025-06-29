package com.clarkelamothe.notemark.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clarkelamothe.notemark.core.domain.SessionStorage
import com.clarkelamothe.notemark.feature_note.domain.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotesViewModel(
    sessionStorage: SessionStorage,
    repository: NoteRepository
) : ViewModel() {
    private val _state = MutableStateFlow(NotesState())
    val state = _state.asStateFlow()

    init {
        setInitials(sessionStorage)

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

    private fun setInitials(sessionStorage: SessionStorage) {
        viewModelScope.launch {
            val username = sessionStorage.get()?.username ?: ""
            _state.update {
                it.copy(
                    initials = when {
                        username.isBlank() -> ""
                        !username.contains(" ") -> username.take(2)
                        else -> {
                            val words = username.split(" ").filter { it.isNotEmpty() }
                            "${words.first().take(1)}${words.last().take(1)}"
                        }
                    }.uppercase()
                )
            }
        }
    }

    fun onAction(action: NotesAction) {
        when (action) {
            NotesAction.OnCreateNote -> TODO()
            NotesAction.OnDeleteNote -> TODO()
            else -> {} /* No-op */
        }
    }
}
