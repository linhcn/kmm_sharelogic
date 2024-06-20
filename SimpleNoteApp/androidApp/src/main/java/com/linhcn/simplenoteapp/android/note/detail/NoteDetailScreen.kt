package com.linhcn.simplenoteapp.android.note.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.linhcn.simplenoteapp.android.MyApplicationTheme
import com.linhcn.simplenoteapp.android.components.TransparentTextField
import com.linhcn.simplenoteapp.android.components.stringResource
import com.linhcn.simplenoteapp.presentation.nav.note.detail.NoteDetailComponent
import com.linhcn.simplenoteapp.resources.SharedRes
import dev.icerock.moko.resources.desc.desc

@Composable
fun NoteDetailScreen(
    noteDetailsComponent: NoteDetailComponent,
) {

    val state by noteDetailsComponent.state.subscribeAsState();

    LaunchedEffect(key1 = true, block = {
        noteDetailsComponent.loadNote()
    })

    NoteDetailContent(
        onBackClick = { noteDetailsComponent.onBackClicked() },
        onSaveClick = noteDetailsComponent::onSaveNoteClicked,
        onTitleChange = noteDetailsComponent::onChangeNoteTitle,
        onContentChange = noteDetailsComponent::onChangeNoteContent,
        state = state
    )
}

@Composable
fun NoteDetailContent(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    state: NoteDetailComponent.State,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(15.dp)
    ) {
        NoteDetailHeader(
            onBackClick = onBackClick,
            onSaveClick = onSaveClick
        )
        NoteDetailEditor(
            noteTitle = state.noteTitle,
            noteContent = state.noteContent,
            onTitleChange = onTitleChange,
            onContentChange = onContentChange,
            modifier = Modifier
                .padding(20.dp)
                .padding(top = 10.dp)
        )
    }
}

@Composable
fun NoteDetailHeader(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onBackClick,
            modifier = Modifier.size(50.dp),
            shape = MaterialTheme.shapes.large,
            colors = ButtonColors(
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.secondary,
            ),
            contentPadding = PaddingValues(5.dp)
        ) {
            Image(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back",
                modifier = Modifier.size(30.dp),
                colorFilter = ColorFilter.lighting(
                    MaterialTheme.colorScheme.onSecondary,
                    MaterialTheme.colorScheme.onSecondary,
                )
            )
        }
        Button(
            onClick = onSaveClick,
            modifier = Modifier.size(50.dp),
            shape = MaterialTheme.shapes.large,
            colors = ButtonColors(
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.secondary,
            ),
            contentPadding = PaddingValues(5.dp)
        ) {
            Image(
                imageVector = Icons.Default.Check,
                contentDescription = "Save",
                modifier = Modifier.size(30.dp),
                colorFilter = ColorFilter.lighting(
                    MaterialTheme.colorScheme.onSecondary,
                    MaterialTheme.colorScheme.onSecondary,
                )
            )
        }
    }
}

@Composable
fun NoteDetailEditor(
    noteTitle: String,
    noteContent: String,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TransparentTextField(
            value = noteTitle,
            hint = stringResource(id = SharedRes.strings.title),
            isHintVisible = noteTitle.isEmpty(),
            onValueChange = onTitleChange,
            onFocusChanged = {},
            textStyle = TextStyle(fontSize = 26.sp, color = MaterialTheme.colorScheme.onSurface)
        )
        Spacer(modifier = Modifier.height(20.dp))
        TransparentTextField(
            value = noteContent,
            hint = stringResource(id = SharedRes.strings.type_something),
            isHintVisible = noteContent.isEmpty(),
            onValueChange = onContentChange,
            onFocusChanged = {},
            textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurface)
        )
    }
}

@Composable
@Preview
fun NoteDetailScreenPreview() {
    MyApplicationTheme(darkTheme = false) {
        Surface {
            NoteDetailContent(
                onBackClick = { /*TODO*/ },
                onSaveClick = { /*TODO*/ },
                onTitleChange = {},
                onContentChange = {},
                state = NoteDetailComponent.State()
            )
        }
    }
}