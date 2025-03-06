import HotwireNative
import UIKit

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        configureHotwire()
        return true
    }

    private func configureHotwire() {
        Hotwire.registerBridgeComponents([
            ButtonComponent.self
        ])
    }
}
