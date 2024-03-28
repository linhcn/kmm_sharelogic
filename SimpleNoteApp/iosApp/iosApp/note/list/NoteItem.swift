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
    var onDeleteClick: () -> Void

    
    var body: some View {
        VStack (alignment: .leading) {
            HStack {
                Text(note.title)
                    .font(.title3)
                    .fontWeight(.semibold)
                Spacer()
                Button(action: onDeleteClick) {
                    Image(systemName: "xmark")
                }
            }.padding(.bottom, 3)
            
            Text(note.content)
                .fontWeight(.light)
                .padding(.bottom, 3)
        }
        .padding()
        .background(Color(hex: note.colorHex))
        .clipShape(RoundedRectangle(cornerRadius: 6.0))
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
            onDeleteClick: {}
        )
    }
}
