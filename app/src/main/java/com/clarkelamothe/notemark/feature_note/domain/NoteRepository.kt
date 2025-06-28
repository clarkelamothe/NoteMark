package com.clarkelamothe.notemark.feature_note.domain

import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<NoteBM>>
}
