//
//  RootView.swift
//  iosApp
//
//  Created by Cao Linh on 02/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

import shared

struct RootView: View {
    
    let root: RootComponent
    
    var body: some View {
        StackView(
            stackValue: StateValue(root.childStack),
            getTitle: {
                switch $0 {
                case is RootComponentChild.NoteListChild: return "List"
                case is RootComponentChild.NoteDetailChild: return "Details"
                default: return ""
                }
            },
            onBack: { toIndex in
                
            },
            childContent: {
                switch $0 {
                case let child as RootComponentChild.NoteListChild:
                    NoteListScreen(noteListComponent: child.componentContext)
                case let child as RootComponentChild.NoteDetailChild:
                    NoteDetailScreen(noteDetailComponent: child.componentContext)
                default: EmptyView()
                }
            }
        )
    }
}

