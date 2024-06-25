//
//  NoteDetailScreen.swift
//  iosApp
//
//  Created by Cao Linh on 09/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteDetailScreen: View {
    
    private var noteDetailComponent: NoteDetailComponent
    
    @StateValue
    private var model: NoteDetailComponentState
    
    init(noteDetailComponent: NoteDetailComponent) {
        self.noteDetailComponent = noteDetailComponent
        _model = StateValue(noteDetailComponent.state)
    }
    
    var body: some View {
        ScrollView {
            VStack {
                TextField(
                    SharedRes.strings().title.desc().localized(),
                    text: Binding(
                        get: {model.noteTitle},
                        set: { value in
                            noteDetailComponent.onChangeNoteTitle(title: value)
                        }
                    )).font(.customFont(size: 18.0))
                Spacer(minLength: 20)
                TextField(
                    SharedRes.strings().type_something.desc().localized(),
                    text: Binding(
                        get: {model.noteContent},
                        set: {value in
                            noteDetailComponent.onChangeNoteContent(noteContent: value)
                        }
                    )).font(.customFont(size: 14.0))
                Spacer()
            }
            .padding()
            .onAppear {
                noteDetailComponent.loadNote()
            }
        }
        .background(Color(hex: model.colorHex))
        .navigationBarBackButtonHidden(true)
        .navigationBarItems(
            leading: Button(
                action: {
                    noteDetailComponent.onBackClicked()
                },
                label: { Image(systemName: "chevron.backward") }
            ),
            trailing: Button(
                action: {
                    noteDetailComponent.onSaveNoteClicked()
                    noteDetailComponent.onBackClicked()
                },
                label: {Image(systemName: "checkmark")}
            )
        )
    }
    
}

struct NoteDetailScreen_Previews: PreviewProvider {
    
    static var previews: some View {
        NoteDetailScreen(noteDetailComponent: DefaultNoteDetailComponent(
            componentContext: DefaultComponentContext(lifecycle: LifecycleRegistryKt.LifecycleRegistry()),
            noteDataSource: DatabaseModule().noteDataSource,
            noteId: -1,
            onFinished: {}
        ))
    }
}
