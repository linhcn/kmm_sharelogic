package com.linhcn.simplenoteapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.createFontFamilyResolver
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.linhcn.simplenoteapp.android.note.detail.NoteDetailScreen
import com.linhcn.simplenoteapp.android.note.list.NoteListScreen
import com.linhcn.simplenoteapp.domain.note.NoteDataSource
import com.linhcn.simplenoteapp.presentation.nav.note.root.DefaultRootComponent
import com.linhcn.simplenoteapp.presentation.nav.note.root.RootComponent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var noteDataSource: NoteDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = DefaultRootComponent(
            componentContext = defaultComponentContext(),
            noteDataSource = noteDataSource
        )

        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    RootContent(rootComponent = root, Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun RootContent(rootComponent: RootComponent, modifier: Modifier = Modifier) {
    Children(
        modifier = modifier,
        stack = rootComponent.childStack,
        animation = stackAnimation(slide())
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.NoteListChild -> NoteListScreen(noteListComponent = child.componentContext)
            is RootComponent.Child.NoteDetailChild -> NoteDetailScreen(noteDetailsComponent = child.componentContext)
        }
    }
}