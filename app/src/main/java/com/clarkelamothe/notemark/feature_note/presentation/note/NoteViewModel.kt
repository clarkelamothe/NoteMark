package com.clarkelamothe.notemark.feature_note.presentation.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {
    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()

    private val eventChannel = Channel<NoteEvent>()
    val events = eventChannel.receiveAsFlow()

    private val initialNote = MutableStateFlow(Pair("", ""))

    private val title = MutableStateFlow("")
    private val description = MutableStateFlow("")
    private val editMode = MutableStateFlow(false)

    init {
        combine(
            title,
            description,
            editMode
        ) { title, description, editMode ->
            val canSave =
                title != initialNote.value.first || description != initialNote.value.second
            _state.update {
                it.copy(
                    title = title,
                    description = description,
                    canSaveNote = canSave,
                    isEditMode = editMode
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onAction(action: NoteAction) {
        when (action) {
            NoteAction.OnCloseClick -> viewModelScope.launch {
                if (_state.value.canSaveNote) {
                    eventChannel.send(NoteEvent.ShowDialog)
                } else {
                    eventChannel.send(NoteEvent.DismissNote)
                }
            }

            is NoteAction.OnDescriptionChange -> description.update { action.description }
            is NoteAction.OnTitleChange -> title.update { action.title }
            is NoteAction.LoadInitialNote -> if (action.id == null) {
                title.update { _state.value.title }
                description.update { _state.value.description }
                editMode.update { true }
            } else {
                // search in local db
                title.update { "Note From Local DB" }
                description.update { "Description From Local DB" }
            }

            NoteAction.EnterEditMode -> editMode.update { true }
            NoteAction.OnDiscardNote -> {
                // delete newly created note
                viewModelScope.launch {
                    eventChannel.send(NoteEvent.DismissNote)
                }
            }
        }
    }
}
