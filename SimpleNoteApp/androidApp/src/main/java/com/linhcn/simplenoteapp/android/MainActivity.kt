package com.linhcn.simplenoteapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.linhcn.simplenoteapp.android.note.detail.NoteDetailScreen
import com.linhcn.simplenoteapp.android.note.list.NoteListScreen
import com.linhcn.simplenoteapp.presentation.nav.DefaultRootComponent
import com.linhcn.simplenoteapp.presentation.nav.RootComponent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = DefaultRootComponent(
            componentContext = defaultComponentContext(),
        )

        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    RootContent(component = root, Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    Children(stack = component.stack, modifier = modifier, animation = stackAnimation(slide())) {
        when (val child = it.instance) {
            is RootComponent.Child.NoteList -> NoteListScreen(noteListComponent = child.component)
            is RootComponent.Child.NoteDetails -> NoteDetailScreen(noteDetailsComponent = child.component)
        }
    }
}