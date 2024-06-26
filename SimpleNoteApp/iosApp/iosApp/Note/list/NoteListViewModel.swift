//
//  NoteListViewModel.swift
//  iosApp
//
//  Created by Cao Linh on 09/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension NoteListScreen {
    
    @MainActor class NoteListViewModel : ObservableObject {
        
        private var noteDataSource: NoteDataSource? = nil
        private let searchNotes = SearchNotes()
        
        private var notes = [Note]()
        @Published private(set) var filteredNotes = [Note]()
        @Published var searchValue = "" {
            didSet {
                self.filteredNotes = searchNotes.invoke(
                    notes: self.notes, query: searchValue
                )
            }
        }
        @Published private(set) var isSearchActive = false
        
        init(noteDataSource: NoteDataSource? = nil) {
            self.noteDataSource = noteDataSource
        }
        
        func loadNotes() {
            noteDataSource?.getAllNotes(completionHandler: { notes, error in
                self.notes = notes ?? []
                self.filteredNotes = self.notes
            })
        }
        
        func deleteNoteById(id: Int64?) {
            if id != nil {
                noteDataSource?.deleteNoteById(id: id!, completionHandler: { error in
                    self.loadNotes()
                })
            }
        }
        
        func onActiveSearch() {
            isSearchActive = !isSearchActive
            if !isSearchActive {
                searchValue = ""
            }
        }
        
        func setNoteDataSource(noteDataSource:  NoteDataSource) {
            self.noteDataSource = noteDataSource
        }
    }
    
}
