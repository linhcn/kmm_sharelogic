package com.linhcn.simplenoteapp.android.note.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linhcn.simplenoteapp.domain.note.Note
import com.linhcn.simplenoteapp.domain.note.NoteDataSource
import com.linhcn.simplenoteapp.domain.time.DateTimeUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val noteTitle = savedStateHandle.getStateFlow("note_title", "");
    private val noteContent = savedStateHandle.getStateFlow("note_content", "");
    private val noteColor = savedStateHandle.getStateFlow("note_color", Note.generateRandomColor())

    private val _hasNoteBeenSaved = MutableStateFlow(false)
    val hasNoteBeenSaved = _hasNoteBeenSaved.asStateFlow()

    val state = combine(noteTitle, noteContent, noteColor) { title, content, color ->
        NoteDetailState(title, content, color)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteDetailState())

    private var existingNoteId: Long? = null

    fun loadNote(id: Long?) {
        if (id == null) {
            return
        }
        this.existingNoteId = id

        viewModelScope.launch {
            noteDataSource.getNoteById(existingNoteId!!)?.let { note ->
                savedStateHandle["note_title"] = note.title
                savedStateHandle["note_content"] = note.content
                savedStateHandle["note_color"] = note.colorHex
            }
        }
    }

    fun onNoteTitleChanged(text: String) {
        savedStateHandle["note_title"] = text
    }

    fun onNoteContentChanged(text: String) {
        savedStateHandle["note_content"] = text
    }

    fun saveNote() {
        viewModelScope.launch {
            noteDataSource.insertNote(
                Note(
                    id =  if (existingNoteId == -1L) null else existingNoteId,
                    title = noteTitle.value,
                    content = noteContent.value,
                    colorHex = noteColor.value,
                    createDate = DateTimeUtil.now()
                )
            )
            _hasNoteBeenSaved.value = true
        }
    }
}