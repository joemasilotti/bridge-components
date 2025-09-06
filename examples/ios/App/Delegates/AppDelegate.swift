import BridgeComponents
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
            HapticComponent.self,
            MenuComponent.self,
            ReviewPromptComponent.self,
            SearchComponent.self,
            ShareComponent.self,
            ThemeComponent.self,
            ToastComponent.self
        ])

        Hotwire.config.backButtonDisplayMode = .minimal
        Hotwire.config.debugLoggingEnabled = true
    }

    private func configureAppearance() {
        UINavigationBar.appearance().scrollEdgeAppearance = .init()
    }
}
