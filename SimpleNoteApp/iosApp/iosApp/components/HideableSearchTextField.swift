//
//  HideableSearchTextField.swift
//  iosApp
//
//  Created by Cao Linh on 09/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HideableSearchTextField: View {
    
    var onActiveSearch: () -> Void
    var isSearchActive: Bool
    
    @Binding var searchValue: String
    
    var body: some View {
        HStack {
            TextField("Search...", text: $searchValue)
                .textFieldStyle(.roundedBorder)
                .opacity(isSearchActive ?  1: 0)
            
            if !isSearchActive {
                Spacer()
            }
            
            Button(action: onActiveSearch) {
                Image(systemName: isSearchActive ? "xmark" : "magnifyingglass")
                    .foregroundColor(.black)
            }
        }
    }
}

struct HideableSearchTextField_Previews: PreviewProvider {
    
    static var previews: some View {
        
        let searchValue: String = "Searching something"
        
        HideableSearchTextField(
            onActiveSearch: {},
            isSearchActive: true,
            searchValue: .constant(searchValue)
        )
    }
}
