package com.clarkelamothe.notemark.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.clarkelamothe.notemark.core.database.dao.NoteDao
import com.clarkelamothe.notemark.core.database.entity.NoteEntity

@Database(
    entities = [
        NoteEntity::class
    ],
    version = 1
)
abstract class NoteMarkDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}
