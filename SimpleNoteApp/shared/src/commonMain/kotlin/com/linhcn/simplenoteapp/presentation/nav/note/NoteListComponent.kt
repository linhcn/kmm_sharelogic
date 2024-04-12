package com.linhcn.simplenoteapp.presentation.nav.note

import com.arkivanov.decompose.ComponentContext

interface NoteListComponent {
    val onAddNoteClicked: () -> Unit
    val onItemClicked: (id: Long) -> Unit
}

class DefaultListComponent(
    componentContext: ComponentContext,
    override val onAddNoteClicked: () -> Unit,
    override val onItemClicked: (id: Long) -> Unit,
) : NoteListComponent, ComponentContext by componentContext