package com.clarkelamothe.notemark.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "note"
)
data class NoteEntity(
    @PrimaryKey
    @ColumnInfo(name = "note_id")
    val noteId: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "date")
    val date: String
)
