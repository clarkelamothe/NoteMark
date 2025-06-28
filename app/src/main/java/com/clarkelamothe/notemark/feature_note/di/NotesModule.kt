package com.clarkelamothe.notemark.feature_note.di

import com.clarkelamothe.notemark.feature_note.data.remote.RemoteNoteRepository
import com.clarkelamothe.notemark.feature_note.domain.NoteRepository
import com.clarkelamothe.notemark.feature_note.presentation.note.NoteViewModel
import com.clarkelamothe.notemark.feature_note.presentation.notes.NotesViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val notesModule = module {
    singleOf(::RemoteNoteRepository).bind<NoteRepository>()
    viewModelOf(::NoteViewModel)
    viewModelOf(::NotesViewModel)
}
