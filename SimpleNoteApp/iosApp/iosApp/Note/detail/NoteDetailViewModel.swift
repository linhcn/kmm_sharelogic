//
//  NoteDetailViewModel.swift
//  iosApp
//
//  Created by Cao Linh on 30/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension NoteDetailScreen {
    
    @MainActor class NoteDetailViewModel : ObservableObject {
        
        private var noteDataSource: NoteDataSource? = nil
        private var noteId: Int64? = nil
        
        @Published var noteTitle: String = ""
        @Published var noteContent: String = ""
        @Published private(set) var noteColor = Note.companion.generateRandomColor()
        
        init(noteDataSource: NoteDataSource? = nil) {
            self.noteDataSource = noteDataSource
        }
        
        func setParamAndLoadNote(noteDataSource: NoteDataSource, noteId: Int64?) {
            self.noteDataSource = noteDataSource
            loadNoteIfExist(id: noteId)
        }
        
        func loadNoteIfExist(id: Int64?) {
            if id != nil {
                self.noteId = id
                noteDataSource?.getNoteById(id: self.noteId!, completionHandler: { note, error in
                    self.noteTitle = note?.title ?? ""
                    self.noteContent = note?.content ?? ""
                    self.noteColor =  note?.colorHex ?? self.noteColor
                })
            }
        }
        
        func saveNote(onSaved: @escaping () -> Void) {
            noteDataSource?.insertNote(
                note: Note(
                    id: noteId == nil ? nil : KotlinLong(value: noteId!),
                    title: noteTitle,
                    content: noteContent,
                    colorHex: noteColor,
                    createDate: DateTimeUtil().now()
                ),
                completionHandler: { error in
                    onSaved()
                }
            )
        }
    }
}
