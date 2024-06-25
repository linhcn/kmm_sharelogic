//
//  Font.swift
//  iosApp
//
//  Created by Cao Linh on 21/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

extension Font {
    
    enum FontStyle {
        case REGULAR
        case THIN
        case LIGHT
        case EXTRA_LIGHT
    }
    
    static func customFont(_ style: FontStyle = .REGULAR, size: Double) -> Font {
        let sharedFont = SharedRes.fonts()
        
        var fontResource: FontResource
        switch style {
            
        case .REGULAR:
            fontResource = sharedFont.playwrite_regular
        case .LIGHT:
            fontResource = sharedFont.playwrite_light
        case .EXTRA_LIGHT:
            fontResource = sharedFont.playwrite_extralight
        case .THIN:
            fontResource = sharedFont.playwrite_thin
        }
        
        return Font(fontResource.uiFont(withSize: size))
    }
}
