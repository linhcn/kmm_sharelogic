package com.linhcn.simplenoteapp.android.note.list

import com.linhcn.simplenoteapp.domain.note.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchValue: String = "",
    val isSearchActive: Boolean = false
)