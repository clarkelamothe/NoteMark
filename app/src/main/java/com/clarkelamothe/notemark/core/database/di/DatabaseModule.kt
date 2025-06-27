package com.clarkelamothe.notemark.core.database.di

import androidx.room.Room
import com.clarkelamothe.notemark.core.database.NoteMarkDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            NoteMarkDatabase::class.java,
            "NoteMark.db"
        ).build()
    }
}
