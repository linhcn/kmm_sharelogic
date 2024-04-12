package com.linhcn.simplenoteapp.presentation.nav

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.linhcn.simplenoteapp.presentation.nav.RootComponent.Child
import com.linhcn.simplenoteapp.presentation.nav.RootComponent.Child.NoteDetails
import com.linhcn.simplenoteapp.presentation.nav.RootComponent.Child.NoteList
import com.linhcn.simplenoteapp.presentation.nav.note.DefaultDetailsComponent
import com.linhcn.simplenoteapp.presentation.nav.note.DefaultListComponent
import kotlinx.parcelize.Parcelize

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    @Parcelize
    private sealed interface Config: Parcelable {
        data object NoteList : Config
        data class NoteDetails(val id: Long?) : Config
    }

    override val stack: Value<ChildStack<*, Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.NoteList,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(config: Config, componentContext: ComponentContext): Child = when (config) {
        is Config.NoteList ->
            NoteList(DefaultListComponent(
                componentContext = componentContext,
                onAddNoteClicked = {
                    navigation.push(Config.NoteDetails(id = null))
                },
                onItemClicked = { id ->
                    navigation.push(Config.NoteDetails(id = id))
                }
            ))

        is Config.NoteDetails ->
            NoteDetails(
                DefaultDetailsComponent(
                    componentContext,
                    config.id,
                    onBackToNoteListClicked = {
                        navigation.pop()
                    },
                    onSuccessAddNoteClicked = {
                        navigation.pop()
                    }
                )
            )
    }
}