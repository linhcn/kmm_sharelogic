package com.linhcn.simplenoteapp.data.note

import com.linhcn.simplenoteapp.domain.note.Note
import comlinhcnsimplenoteapp.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        colorHex = colorHex,
        createDate = Instant.fromEpochMilliseconds(createDate)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}