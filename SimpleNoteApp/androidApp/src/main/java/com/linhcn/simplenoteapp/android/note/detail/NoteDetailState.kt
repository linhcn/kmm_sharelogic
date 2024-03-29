package com.linhcn.simplenoteapp.android.note.detail

import com.linhcn.simplenoteapp.domain.note.Note

data class NoteDetailState(
    val noteTitle: String  = "",
    val noteContent: String = "",
    val colorHex: Long = Note.generateRandomColor()
)