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
    @State private var isNoteSelected = false
    @State private var selectedNoteId: Int64? = nil
    
    init(noteDataSource: NoteDataSource) {
        self.noteDataSource = noteDataSource
    }
    
    var body: some View {
        VStack {
            ZStack {
                NavigationLink(
                    destination: NoteDetailScreen(),
                    isActive: $isNoteSelected,
                    label: {EmptyView()}
                ).hidden()
                
                HideableSearchTextField(
                    onActiveSearch: {
                        viewModel.onActiveSearch()
                    },
                    onSearchChanged: { value in
                        
                    },
                    isSearchActive: viewModel.isSearchActive,
                    destinationProvider: {
                        NoteDetailScreen()
                    },
                    searchValue: $viewModel.searchValue
                )
                .frame(maxWidth: .infinity, minHeight: 40)
                .padding()
            }
            
            List {
                ForEach(viewModel.filteredNotes, id: \.self.id) { note in
                    Button(action: {
                        isNoteSelected = true
                        selectedNoteId = note.id?.int64Value
                    }) {
                        NoteItem(
                            note: note,
                            onDeleteClick: {
                                viewModel.deleteNoteById(id: note.id?.int64Value)
                            }
                        )
                    }
                }
            }.onAppear {
                viewModel.loadNotes()
            }
            .listStyle(.plain)
            .listRowSeparator(.hidden)
        }.onAppear {
            viewModel.setNoteDataSource(noteDataSource: noteDataSource)
        }
    }
}

struct NoteListScreen_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
