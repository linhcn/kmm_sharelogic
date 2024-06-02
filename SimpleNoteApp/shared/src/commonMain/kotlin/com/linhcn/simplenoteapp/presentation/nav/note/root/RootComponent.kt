package com.linhcn.simplenoteapp.presentation.nav.note.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.linhcn.simplenoteapp.presentation.nav.note.detail.NoteDetailComponent
import com.linhcn.simplenoteapp.presentation.nav.note.list.NoteListComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class NoteListChild(val componentContext: NoteListComponent): Child()
        class NoteDetailChild(val componentContext: NoteDetailComponent): Child()
    }
}