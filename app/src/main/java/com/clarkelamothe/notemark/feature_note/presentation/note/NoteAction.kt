package com.clarkelamothe.notemark.feature_note.presentation.note

sealed interface NoteAction {
    data object OnCloseClick : NoteAction
    data class OnTitleChange(val title: String) : NoteAction
    data class OnDescriptionChange(val description: String) : NoteAction
    data class LoadInitialNote(val id: String?) : NoteAction
    data object EnterEditMode : NoteAction
}
