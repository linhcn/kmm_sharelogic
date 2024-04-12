package com.linhcn.simplenoteapp.presentation.nav.note

import com.arkivanov.decompose.ComponentContext

interface NoteDetailsComponent {
    val id: Long?

    val onBackToNoteListClicked: () -> Unit

    val onSuccessAddNoteClicked: () -> Unit
}

class DefaultDetailsComponent(
    componentContext: ComponentContext,
    override val id: Long?,
    override val onBackToNoteListClicked: () -> Unit,
    override val onSuccessAddNoteClicked: () -> Unit,
) : NoteDetailsComponent, ComponentContext by componentContext {
}