package com.linhcn.simplenoteapp.android.note.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linhcn.simplenoteapp.domain.note.Note
import com.linhcn.simplenoteapp.domain.note.NoteDataSource
import com.linhcn.simplenoteapp.domain.note.SearchNotes
import com.linhcn.simplenoteapp.domain.time.DateTimeUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val searchNotes = SearchNotes()

    private val notes = savedStateHandle.getStateFlow("notes", emptyList<Note>())
    private val searchValue = savedStateHandle.getStateFlow("searchValue", "")
    private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)

    val state = combine(notes, searchValue, isSearchActive) { notes, searchValue, isSearchActive ->
        NoteListState(notes, searchValue, isSearchActive)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())

    init {
        viewModelScope.launch {
            (1..20).forEach {
                noteDataSource.insertNote(
                    Note(
                        id = null,
                        title = "Note $it",
                        content = "Content $it",
                        colorHex = Note.generateRandomColor(),
                        createDate = DateTimeUtil.now()
                    )
                )
            }
        }
    }

    fun loadNotes() {
        viewModelScope.launch {
            savedStateHandle["notes"] = noteDataSource.getAllNotes()
        }
    }

    fun onSearchValueChange(value: String) {
        savedStateHandle["searchValue"] = value
    }

    fun onActiveSearch() {
        savedStateHandle["isSearchActive"] = !isSearchActive.value
    }

    fun deleteNoteById(id: Long) {
        viewModelScope.launch {
            noteDataSource.deleteNoteById(id)
            loadNotes()
        }
    }
}