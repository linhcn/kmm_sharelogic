package com.linhcn.simplenoteapp.android.note.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arkivanov.decompose.ComponentContext
import com.linhcn.simplenoteapp.android.R
import com.linhcn.simplenoteapp.android.components.HideableSearchTextField
import com.linhcn.simplenoteapp.presentation.nav.note.NoteListComponent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListScreen(
    noteListComponent: NoteListComponent,
    viewModel: NoteListViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true, block = {
        viewModel.loadNotes()
    })

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = noteListComponent.onAddNoteClicked) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add note",
                    tint = Color.White
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
                    onValueChange = viewModel::onSearchValueChange,
                    onSearchClick = viewModel::onActiveSearch,
                    onCloseClick = viewModel::onActiveSearch,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    hint = stringResource(id = R.string.search_by_the_keyword)
                )

                this@Column.AnimatedVisibility(
                    visible = !state.isSearchActive,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = stringResource(id = R.string.notes),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                }
            }
            LazyColumn {
                items(
                    items = state.notes,
                    key = {
                        it.id.toString()
                    }
                ) { note ->
                    NoteItem(
                        note = note,
                        onNoteClick = {
                            noteListComponent.onItemClicked(note.id!!)
                        },
                        onNoteDelete = { viewModel.deleteNoteById(note.id!!) },
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