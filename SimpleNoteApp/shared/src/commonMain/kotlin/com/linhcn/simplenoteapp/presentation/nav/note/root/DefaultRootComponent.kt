package com.linhcn.simplenoteapp.presentation.nav.note.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.linhcn.simplenoteapp.domain.note.NoteDataSource
import com.linhcn.simplenoteapp.presentation.nav.note.detail.DefaultNoteDetailComponent
import com.linhcn.simplenoteapp.presentation.nav.note.list.DefaultNoteListComponent
import com.linhcn.simplenoteapp.presentation.nav.note.detail.NoteDetailComponent
import com.linhcn.simplenoteapp.presentation.nav.note.list.NoteListComponent
import com.linhcn.simplenoteapp.presentation.nav.note.root.RootComponent.Child
import kotlinx.serialization.Serializable

class DefaultRootComponent(
    componentContext: ComponentContext,
    val noteDataSource: NoteDataSource
) : ComponentContext by componentContext, RootComponent {

    @Serializable
    private sealed class Config {
        @Serializable
        data object NoteList : Config()

        @Serializable
        data class NoteDetail(val noteId: Long) : Config()
    }

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, Child>> = childStack(
        source = navigation,
        serializer = null, // null to disable navigation state saving
        initialConfiguration = Config.NoteList,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): Child = when (config) {
        is Config.NoteList -> Child.NoteListChild(
            createNoteListComponent(componentContext, noteDataSource)
        )

        is Config.NoteDetail -> Child.NoteDetailChild(
            createNoteDetailComponent(componentContext, config, noteDataSource)
        )
    }

    private fun createNoteListComponent(
        componentContext: ComponentContext,
        noteDataSource: NoteDataSource
    ): NoteListComponent = DefaultNoteListComponent(
        componentContext = componentContext,
        onOpenNoteDetail = {
            navigation.push(Config.NoteDetail(it))
        },
        noteDataSource = noteDataSource
    )

    private fun createNoteDetailComponent(
        componentContext: ComponentContext,
        config: Config.NoteDetail,
        noteDataSource: NoteDataSource
    ): NoteDetailComponent = DefaultNoteDetailComponent(
        componentContext = componentContext,
        noteId = config.noteId,
        noteDataSource = noteDataSource,
        onFinished = { navigation.pop() }
    )
}