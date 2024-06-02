package com.linhcn.simplenoteapp.presentation.nav.note.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.linhcn.simplenoteapp.domain.note.NoteDataSource
import com.linhcn.simplenoteapp.domain.note.SearchNotes
import com.linhcn.simplenoteapp.presentation.nav.note.list.NoteListComponent.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DefaultNoteListComponent(
    componentContext: ComponentContext,
    private val onOpenNoteDetail: (id: Long) -> Unit,
    private val noteDataSource: NoteDataSource,
) : NoteListComponent, ComponentContext by componentContext {

    private val searchNotes = SearchNotes()

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val _state = MutableValue(State())
    override val state: Value<State> = _state

    init {
        lifecycle.doOnDestroy(scope::cancel)
    }

    override fun onNoteItemClicked(id: Long) {
        onOpenNoteDetail(id)
    }

    override fun onAddNoteClicked() {
        onOpenNoteDetail(-1)
    }

    override fun onSearchNotes(text: String) {
        scope.launch {
            val noteList = searchNotes.invoke(_state.value.noteList, _state.value.searchValue)
            _state.update {
                it.copy(noteList = noteList, searchValue = text)
            }
        }
    }

    override fun onActiveSearchClicked() {
        _state.update {
            it.copy(isSearchActive = !_state.value.isSearchActive)
        }
    }

    override fun loadNotes() {
        scope.launch {
            val noteList = noteDataSource.getAllNotes()
            _state.update {
                it.copy(noteList = noteList)
            }
        }
    }

    override fun deleteNoteById(id: Long) {
        scope.launch {
            noteDataSource.deleteNoteById(id)
        }
    }

    override fun onActiveSearch() {
        TODO("Not yet implemented")
    }

    override fun onItemClicked(id: Long) {
        onOpenNoteDetail.invoke(id)
    }

    override fun onSearchValueChange(query: String) {
        TODO("Not yet implemented")
    }
}