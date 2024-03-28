package com.linhcn.simplenoteapp.di

import com.linhcn.simplenoteapp.NoteDatabase
import com.linhcn.simplenoteapp.data.DatabaseDriverFactory
import com.linhcn.simplenoteapp.data.note.SqlDelightNoteDataSource

class DatabaseModule {
    private val factory by lazy { DatabaseDriverFactory() }

    val noteDataSource by lazy {
        SqlDelightNoteDataSource(NoteDatabase(factory.createDriver()))
    }
}