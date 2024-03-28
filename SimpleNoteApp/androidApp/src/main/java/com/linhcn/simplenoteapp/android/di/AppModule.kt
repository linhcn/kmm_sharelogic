package com.linhcn.simplenoteapp.android.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import com.linhcn.simplenoteapp.NoteDatabase
import com.linhcn.simplenoteapp.data.DatabaseDriverFactory
import com.linhcn.simplenoteapp.data.note.SqlDelightNoteDataSource
import com.linhcn.simplenoteapp.domain.note.NoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSqlDelightDriver(application: Application): SqlDriver {
        return DatabaseDriverFactory(application).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(sqlDriver: SqlDriver): NoteDataSource {
        return SqlDelightNoteDataSource(NoteDatabase.invoke(sqlDriver))
    }
}