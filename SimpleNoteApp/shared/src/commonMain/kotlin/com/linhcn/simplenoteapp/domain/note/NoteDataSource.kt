package com.linhcn.simplenoteapp.domain.note

interface NoteDataSource {
    suspend fun insertNote(note: Note)
    suspend fun deleteNoteById(id: Long)
    suspend fun getAllNotes(): List<Note>
    suspend fun getNoteById(id: Long): Note?
}