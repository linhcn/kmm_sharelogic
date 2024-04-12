package com.linhcn.simplenoteapp.presentation.nav

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.linhcn.simplenoteapp.presentation.nav.note.NoteDetailsComponent
import com.linhcn.simplenoteapp.presentation.nav.note.NoteListComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        class NoteList(val component: NoteListComponent) : Child()
        class NoteDetails(val component: NoteDetailsComponent) : Child()
    }
}