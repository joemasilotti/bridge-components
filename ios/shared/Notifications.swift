import Foundation
import HotwireNative
import UIKit

public extension Bridgework {
    /// Wrapper to tie `Notification.Name` to a payload type.
    struct BridgeNotification<Payload> {
        let name: Notification.Name

        public init(_ name: String) {
            self.name = .init(name)
        }
    }

    /// Observe an event with a strongly typed payload.
    ///
    /// ```
    /// Bridgework.observe(.themeDidChange) { theme in
    ///     print(theme)
    /// }
    /// ```
    @discardableResult
    static func observe<Payload>(_ typed: BridgeNotification<Payload>, using handler: @escaping (Payload) -> Void) -> NSObjectProtocol {
        NotificationCenter.default.addObserver(forName: typed.name, object: nil, queue: .main) { notification in
            if let payload = notification.userInfo?["payload"] as? Payload {
                handler(payload)
            }
        }
    }

    /// Post an event with a strongly typed payload.
    ///
    /// ```
    /// Bridgework.post(.didReceiveNotificationToken, deviceToken)
    /// ```
    static func post<Payload>(_ typed: BridgeNotification<Payload>, _ payload: Payload) {
        NotificationCenter.default.post(name: typed.name, object: nil, userInfo: ["payload": payload])
    }
}

// MARK: - Events

public extension Bridgework.BridgeNotification where Payload == UIUserInterfaceStyle {
    /// Posted when ``ThemeComponent`` detects a theme change.
    static let themeDidChange = Bridgework.BridgeNotification<UIUserInterfaceStyle>("theme-did-change")
}
