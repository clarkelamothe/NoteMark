package com.clarkelamothe.notemark.feature_note.data.remote

import com.clarkelamothe.notemark.feature_note.domain.NoteBM
import com.clarkelamothe.notemark.feature_note.domain.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

class RemoteNoteRepository: NoteRepository {
    override fun getNotes(): Flow<List<NoteBM>> {
        return flowOf(emptyList())
    }
}
