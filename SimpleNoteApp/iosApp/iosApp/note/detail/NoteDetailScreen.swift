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
    
    private var noteDataSource: NoteDataSource
    private var noteId: Int64? = nil
    
    @StateObject var viewModel: NoteDetailViewModel = NoteDetailViewModel(noteDataSource: nil)
    @Environment(\.presentationMode) var presentationMode
    
    init(noteDataSource: NoteDataSource, noteId: Int64? = nil) {
        self.noteDataSource = noteDataSource
        self.noteId = noteId
    }
    
    var body: some View {
        ScrollView {
            VStack {
                TextField("Title", text: $viewModel.noteTitle)
                    .font(.title)
                Spacer(minLength: 20)
                TextField("Type something...", text: $viewModel.noteContent)
                    .font(.body)
                Spacer()
            }.padding()
        }
        .navigationBarBackButtonHidden(true)
        .navigationBarItems(
            leading: Button(
                action: {presentationMode.wrappedValue.dismiss()},
                label: { Image(systemName: "chevron.backward") }
            )
            ,
            trailing: Button(
                action: {
                    viewModel.saveNote {
                        presentationMode.wrappedValue.dismiss()
                    }
                },
                label: {Image(systemName: "checkmark")}
            )
            
        )
        .onAppear {
            viewModel.setParamAndLoadNote(noteDataSource: self.noteDataSource, noteId: self.noteId)
        }
    }
    
}

struct NoteDetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
