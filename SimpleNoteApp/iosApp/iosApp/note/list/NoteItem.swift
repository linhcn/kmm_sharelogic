//
//  NoteItem.swift
//  iosApp
//
//  Created by Cao Linh on 28/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteItem: View {
    
    var note: Note
    var onDeleteClick: (Note) -> Void
    var onNoteItemClick: (Note) -> Void
    
    var body: some View {
        VStack (alignment: .leading) {
            HStack {
                Text(note.title)
                    .font(.customFont(size: 16.0))
                Spacer()
                
                Button(action: {onDeleteClick(note)}) {
                    Image(systemName: "xmark")
                }
                .buttonStyle(.plain)
                
            }.padding(.bottom, 3)
            
            Text(note.content)
                .padding(.bottom, 3)
                .font(.customFont(.LIGHT, size: 14))
        }
        .padding()
        .background(Color(hex: note.colorHex))
        .clipShape(RoundedRectangle(cornerRadius: 6.0))
        .onTapGesture {
            onNoteItemClick(note)
        }
    }
}

struct NoteItem_Previews: PreviewProvider {
    static var previews: some View {
        NoteItem(
            note: Note(
                id: nil,
                title: "Note",
                content: "Content",
                colorHex: 0xFF1234,
                createDate: DateTimeUtil().now()
            ),
            onDeleteClick: {note in},
            onNoteItemClick: {note in}
        )
    }
}
