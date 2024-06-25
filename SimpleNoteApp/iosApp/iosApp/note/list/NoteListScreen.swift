//
//  NoteListScreen.swift
//  iosApp
//
//  Created by Cao Linh on 09/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine

struct NoteListScreen: View {
    
    private var noteListComponent: NoteListComponent
    
    @StateValue
    private var model: NoteListComponentState

    
    init(noteListComponent: NoteListComponent) {
        self.noteListComponent = noteListComponent
        _model = StateValue(noteListComponent.state)
    }
    
    var body: some View {
        VStack (alignment: .trailing){
            header
                .padding(.horizontal)
            Spacer()
            ZStack (alignment: .center){
                if (model.noteList.isEmpty) {
                    Image(resource: \.box)
                        .resizable()
                        .scaledToFit()
                        .frame(minWidth: 50)
                } else {
                    noteList
                }
            }.onAppear {
                noteListComponent.loadNotes()
            }
            Spacer()
        }.frame(maxWidth: .infinity, maxHeight: .infinity)
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
    }
    
    var noteList: some View {
        List {
            ForEach(model.noteList, id: \.self.id) { note in
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
        NoteListScreen(noteListComponent: DefaultNoteListComponent(
            componentContext: DefaultComponentContext(lifecycle: LifecycleRegistryKt.LifecycleRegistry()),
            onOpenNoteDetail: {id in },
            noteDataSource: DatabaseModule().noteDataSource
        ))
    }
}
