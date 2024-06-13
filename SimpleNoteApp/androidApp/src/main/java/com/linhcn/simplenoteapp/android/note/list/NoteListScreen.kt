package com.linhcn.simplenoteapp.android.note.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.linhcn.simplenoteapp.android.components.HideableSearchTextField
import com.linhcn.simplenoteapp.android.components.stringResource
import com.linhcn.simplenoteapp.presentation.nav.note.list.NoteListComponent
import com.linhcn.simplenoteapp.resources.SharedRes

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListScreen(
    noteListComponent: NoteListComponent
) {

    val state by noteListComponent.state.subscribeAsState()

    LaunchedEffect(key1 = true, block = {
        noteListComponent.loadNotes()
    })

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = noteListComponent::onAddNoteClicked) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    tint = Color.White,
                    contentDescription  = stringResource(id = SharedRes.strings.add_note)
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 15.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                HideableSearchTextField(
                    value = state.searchValue,
                    isSearchActive = state.isSearchActive,
                    onValueChange = noteListComponent::onSearchValueChange,
                    onSearchClick = noteListComponent::onActiveSearch,
                    onCloseClick = noteListComponent::onActiveSearch,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    hint = stringResource(id = SharedRes.strings.search_by_the_keyword)
                )

                this@Column.AnimatedVisibility(
                    visible = !state.isSearchActive,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = stringResource(id = SharedRes.strings.notes),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                }
            }
            LazyColumn {
                items(
                    items = state.noteList,
                    key = {
                        it.id.toString()
                    }
                ) { note ->
                    NoteItem(
                        note = note,
                        onNoteClick = {
                            noteListComponent.onItemClicked(note.id!!)
                        },
                        onNoteDelete = { noteListComponent.deleteNoteById(note.id!!) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 10.dp)
                            .animateItemPlacement()
                    )
                }
            }
        }
    }
}