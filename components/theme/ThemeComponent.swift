import HotwireNative
import UIKit

class ThemeComponent: BridgeComponent {
    override static var name: String { "theme" }

    private var window: UIWindow? {
        let scene = UIApplication.shared.connectedScenes.first as? UIWindowScene
        return scene?.windows.first
    }

    override func onReceive(message: Message) {
        guard let data: MessageData = message.data() else { return }

        switch data.theme {
        case .light:
            window?.overrideUserInterfaceStyle = .light
        case .dark:
            window?.overrideUserInterfaceStyle = .dark
        case .none:
            window?.overrideUserInterfaceStyle = .unspecified
        }
    }
}

private extension ThemeComponent {
    struct MessageData: Decodable {
        let theme: Theme?
    }
}

private extension ThemeComponent {
    enum Theme: String, Decodable {
        case light
        case dark
    }
}
