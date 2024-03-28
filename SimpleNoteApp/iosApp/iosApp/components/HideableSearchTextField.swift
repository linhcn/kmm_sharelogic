//
//  HideableSearchTextField.swift
//  iosApp
//
//  Created by Cao Linh on 28/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct HideableSearchTextField<Destination: View>: View {
    
    
    var onActiveSearch: () -> Void
    var onSearchChanged: (String) -> Void
    var isSearchActive: Bool
    var destinationProvider: () -> Destination
    
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
            NavigationLink(destination: destinationProvider) {
                Image(systemName: "plus")
                    .foregroundColor(.black)
            }
        }
    }
}

struct HideableSearchTextField_Previews: PreviewProvider {
    
    static var previews: some View {
        
        var searchValue: String = "Searching something"
        
        HideableSearchTextField(
            onActiveSearch: {},
            onSearchChanged: { value in
                searchValue = value
            },
            isSearchActive: true,
            destinationProvider: {
                EmptyView()
            },
            searchValue: .constant(searchValue)
        )
    }
}
