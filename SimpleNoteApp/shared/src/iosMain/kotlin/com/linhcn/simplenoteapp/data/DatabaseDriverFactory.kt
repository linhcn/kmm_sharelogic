package com.linhcn.simplenoteapp.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.linhcn.simplenoteapp.NoteDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(NoteDatabase.Schema, "note.db")
    }
}