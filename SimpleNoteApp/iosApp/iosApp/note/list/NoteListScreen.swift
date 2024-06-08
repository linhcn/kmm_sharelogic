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
    
    private var noteListComponent: NoteListComponent
    
    @StateValue
    private var model: NoteListComponentState
    
    init(noteListComponent: NoteListComponent) {
        self.noteListComponent = noteListComponent
        _model = StateValue(noteListComponent.state)
    }
    
    var body: some View {
        VStack {
            header
            noteList
                .onAppear {
                    noteListComponent.loadNotes()
                }
        }
    }
    
    var header: some View {
        HStack {
            HideableSearchTextField(
                onActiveSearch: {
                    noteListComponent.onActiveSearch()
                },
                isSearchActive: model.isSearchActive,
                searchValue: Binding(get: {model.searchValue}, set: { value in
                    noteListComponent.onSearchValueChange(query: value)
                })
            )
            .frame(minHeight: 40)
            Spacer(minLength: 20)
            Button(action: {
                noteListComponent.onAddNoteClicked()
            }, label:{
                Image(systemName: "plus")
            })
        }
        .padding()
    }
    
    var noteList: some View {
        List {
            ForEach(self.model.noteList, id: \.self.id) { note in
                NoteItem(
                    note: note,
                    onDeleteClick: { note in
                        noteListComponent.deleteNoteById(id: note.id!.int64Value)
                    },
                    onNoteItemClick: { note in
                        noteListComponent.onItemClicked(id: note.id!.int64Value)
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
