package com.linhcn.simplenoteapp.presentation.nav.note.detail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.linhcn.simplenoteapp.domain.note.Note
import com.linhcn.simplenoteapp.domain.note.NoteDataSource
import com.linhcn.simplenoteapp.domain.time.DateTimeUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import com.linhcn.simplenoteapp.presentation.nav.note.detail.NoteDetailComponent.State

class DefaultNoteDetailComponent(
    private val componentContext: ComponentContext,
    private val noteDataSource: NoteDataSource,
    private val noteId: Long,
    private val onFinished: () -> Unit
) : NoteDetailComponent, ComponentContext by componentContext {

    private val scope = CoroutineScope(context = Dispatchers.IO)

    private val _state = MutableValue(State())
    override val state: Value<State> = _state

    init {
        lifecycle.doOnDestroy(scope::cancel)
    }

    override fun onBackClicked() = onFinished()

    override fun onSaveNoteClicked() {
        scope.launch {
            noteDataSource.insertNote(
                Note(
                    id = if (noteId == -1L) null else noteId,
                    title = _state.value.noteTitle,
                    content = _state.value.noteContent,
                    colorHex = _state.value.colorHex,
                    createDate = DateTimeUtil.now()
                )
            )
            launch (Dispatchers.Main){
                onFinished()
            }
        }
    }

    override fun loadNote() {
        if (noteId == -1L)
            return

        scope.launch {
            noteDataSource.getNoteById(noteId)?.let { note ->
                _state.update {
                    it.copy(
                        noteTitle = note.title,
                        noteContent = note.content,
                        colorHex = note.colorHex
                    )
                }
            }
        }
    }

    override fun onChangeNoteTitle(title: String) {
        _state.update {
            it.copy(noteTitle = title)
        }
    }

    override fun onChangeNoteContent(noteContent: String) {
        _state.update {
            it.copy(noteContent = noteContent)
        }
    }
}