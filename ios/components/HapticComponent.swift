import CoreHaptics
import HotwireNative
import UIKit

public final class HapticComponent: BridgeComponent {
    override public nonisolated class var name: String { "haptic" }

    override public func onReceive(message: Message) {
        guard let event = Event(rawValue: message.event) else { return }

        switch event {
        case .vibrate:
            handleVibrateEvent(with: message)
        }
    }

    private func handleVibrateEvent(with message: Message) {
        guard let data: MessageData = message.data() else { return }

        switch FeedbackType(rawValue: data.feedback) {
        case .success:
            UINotificationFeedbackGenerator().notificationOccurred(.success)
        case .warning:
            UINotificationFeedbackGenerator().notificationOccurred(.warning)
        case .error:
            UINotificationFeedbackGenerator().notificationOccurred(.error)
        case nil:
            UINotificationFeedbackGenerator().notificationOccurred(.success)
        }
    }
}

private extension HapticComponent {
    enum Event: String {
        case vibrate
    }
}

private extension HapticComponent {
    struct MessageData: Decodable {
        let feedback: String
    }

    enum FeedbackType: String {
        case success
        case warning
        case error
    }
}
