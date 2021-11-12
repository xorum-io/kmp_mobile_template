import UIKit
import common

let store = AppStoreKt.store

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?
    let rootViewController = MainViewController()

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        initMessageHandler()
        initAppStyle()

        return true
    }

    private func initMessageHandler() {
        MessageMiddlewareKt.messageHandlers.add(IosMessageHandler())
    }

    private func initAppStyle() {
        UINavigationBar.appearance().run {
            $0.isTranslucent = false
            $0.barTintColor = .white
            $0.tintColor = .white
        }

        UITabBar.appearance().run {
            $0.isTranslucent = false
            $0.barTintColor = .white
            $0.itemPositioning = .centered
        }

        window = UIWindow(frame: UIScreen.main.bounds)
        window!.rootViewController = rootViewController
        window!.makeKeyAndVisible()

        if #available(iOS 13.0, *) {
            window?.overrideUserInterfaceStyle = .light
        }
    }
}
