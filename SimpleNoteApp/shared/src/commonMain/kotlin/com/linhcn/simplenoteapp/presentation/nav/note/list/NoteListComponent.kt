package com.linhcn.simplenoteapp.presentation.nav.note.list

import com.arkivanov.decompose.value.Value
import com.linhcn.simplenoteapp.domain.note.Note

interface NoteListComponent {

    val state: Value<State>

    fun onNoteItemClicked(id: Long)
    fun onAddNoteClicked()
    fun onSearchNotes(text: String)
    fun onActiveSearchClicked()
    fun deleteNoteById(id: Long)
    fun loadNotes()
    fun onItemClicked(id: Long)
    fun onActiveSearch()
    fun onSearchValueChange(query: String)

    data class State(
        val noteList: List<Note> = listOf(),
        val searchValue: String = "",
        val isSearchActive: Boolean = false
    )
}