package com.linhcn.simplenoteapp.android.note.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhcn.simplenoteapp.android.MyApplicationTheme
import com.linhcn.simplenoteapp.domain.note.Note
import com.linhcn.simplenoteapp.domain.time.DateTimeUtil

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClick: () -> Unit,
    onNoteDelete: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(Color(note.colorHex))
            .clickable { onNoteClick() }
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = note.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                style = TextStyle(color = Color.Black)
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete note",
                modifier = Modifier.clickable { onNoteDelete() }
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = note.content,
            fontWeight = FontWeight.Light,
            style = TextStyle(color = Color.Black)
        )
    }
}

@Preview
@Composable
fun NoteItemPreview() {
    MyApplicationTheme(darkTheme = true) {
        NoteItem(
            note = Note(
                null,
                title = "Example note",
                content = "Example note content",
                Note.generateRandomColor(),
                DateTimeUtil.now()
            ),
            onNoteClick = {},
            onNoteDelete = {}
        )
    }
}