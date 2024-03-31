//
//  NoteListScreen.swift
//  iosApp
//
//  Created by Cao Linh on 09/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteListScreen: View {
    
    private var noteDataSource: NoteDataSource
    @StateObject var viewModel = NoteListViewModel(noteDataSource: nil)
    @State private var noteSelected: Note? = nil
    @State private var isNoteSelected: Bool = false
    
    init(noteDataSource: NoteDataSource) {
        self.noteDataSource = noteDataSource
    }
    
    var body: some View {
        VStack {
            header
            noteList
                .onAppear {
                    viewModel.loadNotes()
                }
        }.onAppear {
            viewModel.setNoteDataSource(noteDataSource: noteDataSource)
        }
        .navigationDestination(isPresented: $isNoteSelected, destination: {
            NoteDetailScreen(noteDataSource: noteDataSource, noteId: noteSelected?.id?.int64Value)
        })
    }
    
    var header: some View {
        HStack {
            HideableSearchTextField(
                onActiveSearch: {
                    viewModel.onActiveSearch()
                },
                isSearchActive: viewModel.isSearchActive,
                searchValue: $viewModel.searchValue
            )
            .frame(minHeight: 40)
            Spacer(minLength: 20)
            NavigationLink(destination: NoteDetailScreen(noteDataSource: noteDataSource)) {
                Image(systemName: "plus")
            }
            
        }
        .padding()
    }
    
    var noteList: some View {
        List {
            ForEach(viewModel.filteredNotes, id: \.self.id) { note in
                    NoteItem(
                        note: note,
                        onDeleteClick: {
                            viewModel.deleteNoteById(id: note.id?.int64Value)
                        },
                        onNoteItemClick: { note in
                            isNoteSelected = true
                            noteSelected = note
                        }
                    )
            }
            
        }
        .listStyle(.plain)
        .listRowSeparator(.hidden)
    }
}

struct NoteListScreen_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
