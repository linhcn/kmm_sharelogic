package com.linhcn.simplenoteapp.presentation.nav.note.detail

import com.arkivanov.decompose.value.Value
import com.linhcn.simplenoteapp.domain.note.Note

interface NoteDetailComponent {

    val state: Value<State>

    fun onBackClicked()
    fun onSaveNoteClicked()
    fun loadNote()
    fun onChangeNoteContent(noteContent: String)
    fun onChangeNoteTitle(title: String)

    data class State(
        val noteTitle: String = "",
        val noteContent: String = "",
        val colorHex: Long = Note.generateRandomColor()
    )
}