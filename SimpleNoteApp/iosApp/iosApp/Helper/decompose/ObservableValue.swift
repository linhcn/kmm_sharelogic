//
//  ObservableValue.swift
//  iosApp
//
//  Created by Cao Linh on 02/06/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public class ObservableValue<T : AnyObject> : ObservableObject {
    @Published
    var value: T

    private var cancellation: Cancellation?
    
    init(_ value: Value<T>) {
        self.value = value.value
        self.cancellation = value.subscribe { [weak self] value in self?.value = value }
    }

    deinit { 
        cancellation?.cancel()
    }
}
