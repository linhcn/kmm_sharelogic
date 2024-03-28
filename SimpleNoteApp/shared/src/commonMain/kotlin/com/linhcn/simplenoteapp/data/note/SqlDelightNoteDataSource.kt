package com.linhcn.simplenoteapp.data.note

import com.linhcn.simplenoteapp.NoteDatabase
import com.linhcn.simplenoteapp.domain.note.Note
import com.linhcn.simplenoteapp.domain.note.NoteDataSource
import com.linhcn.simplenoteapp.domain.time.DateTimeUtil

class SqlDelightNoteDataSource(database: NoteDatabase) : NoteDataSource {

    private val queries = database.noteQueries

    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            colorHex = note.colorHex,
            createDate = DateTimeUtil.toEpochMillis(note.createDate)
        )
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNote(id)
    }

    override suspend fun getAllNotes(): List<Note> {
        return queries.getAllNotes().executeAsList()
            .map { it.toNote() }
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries.getNoteById(id).executeAsOneOrNull()?.toNote()
    }
}