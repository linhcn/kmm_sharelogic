package com.linhcn.simplenoteapp.domain.note

class SearchNotes {
    fun invoke(notes: List<Note>, query: String): List<Note> {

        if (query.isBlank()) {
            return notes
        }

        return notes.filter {
            it.title.trim().lowercase().contains(query.lowercase())
        }.sortedBy { it.title }
    }
}