import SwiftUI
import shared

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate
    
	var body: some Scene {
		WindowGroup {
            RootView(root: appDelegate.root)
		}
	}
}

class AppDelegate: NSObject, UIApplicationDelegate {
    
    private let databaseModule = DatabaseModule()
    let root: RootComponent
    
    override init() {
        root = DefaultRootComponent(
            componentContext: DefaultComponentContext(lifecycle: LifecycleRegistryKt.LifecycleRegistry()),
            noteDataSource: databaseModule.noteDataSource
        )
    }
}
