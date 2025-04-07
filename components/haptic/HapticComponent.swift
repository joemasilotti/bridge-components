import HotwireNative
import UIKit

final class HapticComponent: BridgeComponent {
    override class var name: String { "haptic" }

    override func onReceive(message: Message) {
        guard let event = Event(rawValue: message.event) else { return }

        switch event {
        case .vibrate:
            UINotificationFeedbackGenerator().notificationOccurred(.success)
        }
    }
}

private extension HapticComponent {
    enum Event: String {
        case vibrate
    }
}

