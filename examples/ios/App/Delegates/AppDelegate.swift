import HotwireNative
import UIKit

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        configureHotwire()
        configureAppearance()
        return true
    }

    private func configureHotwire() {
        Hotwire.registerBridgeComponents([
            AlertComponent.self,
            ButtonComponent.self,
            FormComponent.self,
            MenuComponent.self,
            ReviewPromptComponent.self,
            ShareComponent.self,
            ToastComponent.self
        ])

        Hotwire.config.backButtonDisplayMode = .minimal
    }

    private func configureAppearance() {
        UINavigationBar.appearance().scrollEdgeAppearance = .init()
    }
}
