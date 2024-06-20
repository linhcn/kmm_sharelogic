//
//  Image.swift
//  iosApp
//
//  Created by Cao Linh on 19/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared


extension Image {
    init(resource: KeyPath<SharedRes.images, ImageResource>) {
        self.init(uiImage: SharedRes.images()[keyPath: resource].toUIImage()!)
    }
}
