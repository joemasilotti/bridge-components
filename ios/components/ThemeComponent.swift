import HotwireNative
import UIKit

public final class ThemeComponent: BridgeComponent {
    override public nonisolated class var name: String { "theme" }

    private var window: UIWindow? {
        let scene = UIApplication.shared.connectedScenes.first as? UIWindowScene
        return scene?.windows.first
    }

    override public func onReceive(message: Message) {
        guard let data: MessageData = message.data() else { return }

        switch data.theme {
        case .light: apply(theme: .light)
        case .dark: apply(theme: .dark)
        default: apply(theme: .unspecified)
        }
    }

    private func apply(theme: UIUserInterfaceStyle) {
        if window?.overrideUserInterfaceStyle != theme {
            window?.overrideUserInterfaceStyle = theme
            Bridgework.post(.themeDidChange, theme)
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
