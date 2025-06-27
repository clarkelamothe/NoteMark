package com.clarkelamothe.notemark.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.clarkelamothe.notemark.core.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<NoteEntity>>
}
