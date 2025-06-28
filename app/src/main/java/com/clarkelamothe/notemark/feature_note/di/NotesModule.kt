package com.clarkelamothe.notemark.feature_note.di

import com.clarkelamothe.notemark.feature_note.presentation.note.NoteViewModel
import com.clarkelamothe.notemark.feature_note.presentation.notes.NotesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val notesModule = module {
    viewModelOf(::NoteViewModel)
    viewModelOf(::NotesViewModel)
}
