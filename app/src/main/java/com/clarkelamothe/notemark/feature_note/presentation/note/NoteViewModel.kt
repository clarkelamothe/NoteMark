package com.clarkelamothe.notemark.feature_note.presentation.note

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteViewModel : ViewModel() {
    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()
}